package TestsAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestWithHamcrest {
    @BeforeClass
    public void setupClass() {

        baseURI="http://54.144.70.64:8000";

    }
    /*
       Given accept type is json
       and path param is 15
       When user sends get request to "/api/spartans/{id}"
         Then status code is 200
         And content type is "application/json"
         And response payload values match the following:
               id is 15,
               name is "Meta"
               gender is "Female"
               phone is "1938695106"
     */

    @Test
    public void test1() {
                given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .assertThat().contentType("application/json");


    }

    @Test
    public void test2() {

        given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id",equalTo(15),"name",equalTo("Meta"),"gender", equalTo("Female"),"phone",equalTo(1938695106));

    }
}
