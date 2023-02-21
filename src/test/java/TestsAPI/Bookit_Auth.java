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
public class Bookit_Auth {

    String accessToken="Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";
    @BeforeClass
    public void beforeClass() {
        baseURI="http://cybertek-reservation-api-qa3.herokuapp.com";
    }

    @Test
    public void test1() {

        Response response = given().header("Authorization", accessToken)
                .when().get("/api/campuses");


        assertEquals(response.statusCode(),200);

        response.prettyPrint();


    }
}
