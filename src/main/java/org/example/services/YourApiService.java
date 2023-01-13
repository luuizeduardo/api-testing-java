package org.example.services;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class YourApiService {

    @Value("${apitest.base.uri}")
    private String baseURI;

    @Value("${apitest.base.path}")
    private String basePath;

    @Value("${apitest.token}")
    private String myToken;

    private RequestSpecification spec;

    @PostConstruct
    protected void init() {

        RestAssured.useRelaxedHTTPSValidation();

        spec = new RequestSpecBuilder().setBaseUri(baseURI).setBasePath(basePath).build();
    }

    public Response postRequest(String endpoint, JsonNode requestBody) {

        return RestAssured.given(spec)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(endpoint);
    }

    public Response getRequest(String endpoint) {

        return RestAssured.given(spec)
            // In our case, we won't use the "token" variable, as the API doesn't require so.
            // But if your API require, here you can use the token like this:
            // .auth().basic("token", myToken)
            .contentType(ContentType.JSON)
        .when()
            .get(endpoint);
    }
}
