package APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanTest {

    String spartanBaseURL = "http://54.144.70.64:8000";

    @Test

    public void viewSpartanTest1 (){

        Response response = RestAssured.get(spartanBaseURL + "/api/spartans/");

        System.out.println("response.statusCode() = " + response.statusCode());

        //print response as String
        System.out.println("response.body().asString() = " + response.body().asString());

        //print response as pretty (json)
        System.out.println("response.body().prettyPrint() = " + response.body().prettyPrint());



    }

    /**When user send GET request to/api/spartans end point
     *And body should contains  Allen
     */
    @Test
    public void viewSpartanTest2 (){
        Response response = RestAssured.get(spartanBaseURL + "/api/spartans/");

        //verify status code 200
        Assert.assertEquals(response.statusCode(),200);

        //verify body contains Allen
        Assert.assertTrue(response.body().asString().contains("Allen"));
    }

    /*
    Given accept type is json
    When user sends a get request to spartanAllurl
    Then response status code is 200
    And response body should be json format

     */

    @Test
    public void viewSpartanTest3 (){
        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(spartanBaseURL + "/api/spartans");

        //verify status code 200
        Assert.assertEquals(response.statusCode(),200);


        //verify response type is json
        Assert.assertTrue(response.contentType().contains("application/json"));



    }
}
