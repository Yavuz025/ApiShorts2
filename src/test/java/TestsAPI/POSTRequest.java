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

public class POSTRequest {
    @BeforeClass
    public void setupClass() {
        baseURI="http://54.144.70.64:8000";

    }

    @Test
    public void postWithString() {
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "        \"name\": \"Eric\",\n" +
                        "        \"gender\": \"Male\",\n" +
                        "        \"phone\": 1234567890\n" +
                        "    }")
                .when().post("/api/spartans");
        response.prettyPrint();


        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");
        assertEquals(response.path("success"),"A Spartan is Born!");

        JsonPath json= response.jsonPath();

        assertEquals(json.getString("data.name"),"Eric");
        assertEquals(json.getString("data.gender"),"Male");
        assertEquals(json.getString("data.phone"),"1234567890");



    }

    @Test
    public void postMethodWithMap() {

        Map<String,Object > requestMap=new HashMap<>();

        requestMap.put("name", "EricMap");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 1234567890L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("/api/spartans/");

        assertEquals(response.statusCode(),201);

        response.prettyPrint();



    }

    @Test
    public void postWithPOJO() {
        Spartan spartan= new Spartan();

        spartan.setName("EricPoJO");
        spartan.setGender("Male");
        spartan.setPhone(1234567890L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("/api/spartans/");
        assertEquals(response.statusCode(),201);

        response.prettyPrint();


        System.out.println("==============================================================");


        Response response2 = given().accept(ContentType.JSON)
                .pathParam("id", 123)
                .and().when().get("/api/spartans/{id}");


        Spartan spartanResponse = response2.body().as(Spartan.class);
        System.out.println( spartanResponse.toString());


    }
}
