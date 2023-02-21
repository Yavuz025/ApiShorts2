package TestsAPI;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class PathParameters {
       @BeforeClass
    public void setUpClass(){
       baseURI="http://54.144.70.64:8000";

    }
    @Test
    public void pathParamsId(){
         Response response=given().contentType(ContentType.JSON)
                   .and().pathParam("id",25)
                   .when().get("/api/spartans/{id}");


        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json");

        Assert.assertTrue(response.body().asString().contains("Valentin"));

        response.body().prettyPrint();

        System.out.println(response.contentType());





    }
    @Test
    public void negativePathParams(){
        Response response= given().accept(ContentType.JSON)
                .and().pathParam("id",500)
                .when().get("/api/spartans/{id}");


        Assert.assertEquals(response.statusCode(),404);

        Assert.assertEquals(response.contentType(),"application/json");

        Assert.assertTrue(response.body().asString().contains("Not Found"));

        response.body().prettyPrint();








    }

}
