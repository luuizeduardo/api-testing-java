package org.example.reqres;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.example.repositories.FileUtils;
import org.example.repositories.ResponseUtils;
import org.example.services.YourApiService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReqResTest {

    private final YourApiService yourApiService;

    @Autowired
    public ReqResTest(YourApiService yourApiService) {
        this.yourApiService = yourApiService;
    }

    @Test
    public void testCreateUser() throws IOException {

        JsonNode requestBody = FileUtils.readJsonFromFile("user/createUser.json");

        Response res = yourApiService.postRequest("/users", requestBody);
        assertThat(res.statusCode(), is(equalTo(201)));
    }

    @Test
    public void testGetUser() throws IOException, JSONException {

        JsonNode expectedResponse = FileUtils.readJsonFromFile("responses/user/specific.json");

        Response res = yourApiService.getRequest("/users/2");

        assertThat(res.statusCode(), is(equalTo(200)));
        ResponseUtils.assertJson(res, expectedResponse);
    }
}
