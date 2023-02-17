package APITests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class SpartanTest2 {
    String spartanBaseUrl ="http://54.144.70.64:8000";

    @Test
     public  void spartanTest(){

        Response response= RestAssured.get(spartanBaseUrl+"/api/spartans");

        System.out.println(response.statusCode());

        System.out.println(response.body().prettyPrint());
    }

}
