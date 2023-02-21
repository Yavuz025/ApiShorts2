package TestsAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanTest2 {
    String spartanBaseUrl ="http://54.144.70.64:8000";

    @Test
     public  void spartanTest(){

        Response response= RestAssured.get(spartanBaseUrl+"/api/spartans");

        System.out.println(response.statusCode());

        Assert.assertEquals(response.statusCode(),200);

        //System.out.println(response.body().prettyPrint());

        Assert.assertTrue(response.body().asString().contains("Valentin"));
    }

    @Test
    public void spartanTest2(){
        /*
    Given accept type is json
    When user sends a get request to spartanAllurl
    Then response status code is 200
    And response body should be json format

     */
Response response= RestAssured.given().accept(ContentType.JSON)
        .when().get(spartanBaseUrl+"/api/spartans");

    Assert.assertEquals(response.statusCode(),200);
    Assert.assertEquals(response.contentType(),"application/json");


    }
}
