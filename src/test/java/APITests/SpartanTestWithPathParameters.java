package APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestWithPathParameters {


    @BeforeClass
    public void setupClass() {
        RestAssured.baseURI= "http://18.234.174.132:8000";

    }
    /*
    Given accept type is Json
    And Id parameter value is 10
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content-type: application/json;charset=UTF-8
    And "Allen" should be in response payload

     */


@Test
    public void PathTest1(){
        Response response = RestAssured.given().accept(ContentType.JSON).
                and().pathParam("id", 18)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);


        assertEquals(response.contentType(), "application/json");


        assertTrue(response.body().asString().contains("Allen"));

    System.out.println("response.body().prettyPrint() = " + response.body().prettyPrint());

}
/*
   Given accept type is Json
   And Id parameter value is 500
   When user sends GET request to /api/spartans/{id}
   Then response status code should be 404
   And response content-type: application/json
   And "Spartan Not Found" message should be in response payload
 */

@Test
    public void negativePathParamTest(){

    Response response = RestAssured.given().accept(ContentType.JSON)
            .and().pathParam("id", 500)
            .when().get("/api/spartans/{id}");

    assertEquals(response.statusCode(), 404);

    assertEquals(response.contentType(), "application/json");

    assertTrue(response.body().asString().contains("Not Found"));

    System.out.println("response.body().prettyPrint() = " + response.body().prettyPrint());

}

}
