package APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class SpartanJsonToCollections {

    @BeforeClass
    public void setupClass() {
        RestAssured.baseURI= "http://18.234.174.132:8000";

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
        Response response = RestAssured.given().accept(ContentType.JSON).
                and().pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        Map <String,Object> spartanMap = response.body().as(Map.class);
        //System.out.println("spartanMap = " + spartanMap);

        //System.out.println("spartanMap.get(\"gender\") = " + spartanMap.get("gender"));

        assertEquals(spartanMap.get("name"), "Nona");

    }

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                         .when().get("/api/spartans");

        List <Map<String,Object>> list = response.body().as(List.class);

        //System.out.println(list);

        int counter=1;

        for (Map<String, Object> each : list) {
            System.out.println(counter + "- " + each.get("name"));
            counter++;

        }

    }

}
