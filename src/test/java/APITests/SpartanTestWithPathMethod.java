package APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class SpartanTestWithPathMethod {

    @BeforeClass
    public void setupClass() {

        RestAssured.baseURI= "http://18.234.174.132:8000";

    }

    /*
         Given accept type is json
         And path param id is 10
         When user sends get request to "/api/spartans/{id}"
         Then status code is 200
         And content type is "application/json"
         And response payload values match the following:
               id is 10,
               name is "Lorenza"
               gender is "Female"
               phone is "3312820936"
     */

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json");

        int id = response.path("id");
        String name = response.body().path("name");
        String gender = response.body().path("gender");
        long phone = response.body().path("phone");

        assertEquals(id, 10);
        assertEquals(name, "Lorenza");
        assertEquals(gender, "Female");
        assertEquals(phone, 3312820936l);



    }

    @Test
    public void test2(){
        //short version simple get request without given and when (by default) return type is json
        Response response = RestAssured.get("/api/spartans");

        //extract first id
        int firstId = response.path("id[0]");
        System.out.println(firstId);

        //get the last first name
        String lastFirstName = response.path("name[-1]");
        System.out.println(lastFirstName);

        //extract all Fist Names and print them

        List<String> names= response.path("name");
        System.out.println(names);

        //System.out.println("response.path(\"phone\") = " + response.path("phone"));


    }
}
