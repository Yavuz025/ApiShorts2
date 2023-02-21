package TestsAPI;


import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class DeleteRequest {
    @BeforeClass
    public void setupClass() {
        baseURI="http://54.144.70.64:8000";

    }

    @Test
    public void testDelete() {

        given().pathParam("id",123)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

        given().pathParam("id",123)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(404);

        given().pathParam("id",123)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(404);




    }
}