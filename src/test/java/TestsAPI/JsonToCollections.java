package TestsAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;


import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;

public class JsonToCollections {
    @BeforeClass
    public void setupClass() {
       baseURI="http://54.144.70.64:8000";

    }
/*
         Given accept type is json
         And path param id is 11
         When user sends get request to "/api/spartans/{id}"
         Then status code is 200
         And content type is "application/json"
         And response payload values match the following:
               id is 11,
               name is "Nona"
               gender is "Female"
               phone is "7959094216"
     */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");
//                .then().assertThat().statusCode(200)
//                .and().assertThat().contentType("application/json")
//                .and().assertThat().body("id", equalTo(11),"name",equalTo("Nona"),"gender",equalTo("Female"),"phone",equalTo(7959094216l));


        Map <String,Object> spartanMap= response.body().as(Map.class);

        System.out.println( spartanMap.get("name"));
        System.out.println( spartanMap.get("id"));
        System.out.println(spartanMap);

        Assert.assertEquals(spartanMap.get("name"),"Nona");
        Assert.assertEquals(spartanMap.get("phone"),7.959094216E9);



    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        
        List<Map<String,Object>> listOfSpartans = response.body().as(List.class);
        Map<String, Object> firstSpartan=listOfSpartans.get(0);
        //System.out.println("firstSpartan = " + firstSpartan);

        int counter=1;
        for (Map<String, Object> map : listOfSpartans) {
            System.out.println(counter+ "-Spartan = " + map);
            counter++;
            
        }
        



    }
}
