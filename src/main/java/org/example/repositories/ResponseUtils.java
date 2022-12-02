package org.example.repositories;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.stereotype.Repository;

@Repository
public class ResponseUtils {

    public static void assertJson(String actualJson, String expectedJson, JSONCompareMode mode) throws JSONException {
        JSONAssert.assertEquals(expectedJson, actualJson, mode);
    }

    public static void assertJson(Response response, JsonNode expectedJson) throws JSONException {
        assertJson(response.getBody().asString(), expectedJson.toString(), JSONCompareMode.LENIENT);
    }
}
