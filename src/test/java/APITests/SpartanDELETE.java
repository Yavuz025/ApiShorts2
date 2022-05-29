package APITests;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class SpartanDELETE {
    @BeforeClass
    public void setupClass() {

        RestAssured.baseURI = "http://18.234.174.132:8000";
    }

    @Test
    public void test1(){
        given().pathParam("id", 101)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

        given().pathParam("id", 101)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(404);

        given().accept(ContentType.JSON)
                .pathParam("id", 101)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(404);
    }

}
