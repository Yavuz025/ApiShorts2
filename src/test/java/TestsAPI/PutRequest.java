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

public class PutRequest {
    @BeforeClass
    public void setupClass() {
        baseURI="http://54.144.70.64:8000";

    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .pathParam("id",123)
                .body("{\n" +
                        "    \"name\": \"Mike Mouse\",\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"phone\": 1234567890\n" +
                        "}")
                .when().put("/api/spartans/{id}");

        response.prettyPrint();
        assertEquals(response.statusCode(),204);

    }

    @Test
    public void testPutWithMap() {

        Map<String ,Object> putMap= new HashMap<>();
        putMap.put("name","MikePut");
        putMap.put("gender", "Male");
        putMap.put("phone",9876543210L);


        given().contentType(ContentType.JSON)
                .pathParam("id",123)
                .and().body(putMap)
                .when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204);




    }
    @Test
    public void testPutWithPATCHMap() {

        Map<String ,Object> patchMap= new HashMap<>();
        patchMap.put("name","MikePATCH");


        given().contentType(ContentType.JSON)
                .pathParam("id",123)
                .and().body(patchMap)
                .when().patch("/api/spartans/{id}")
                .then().assertThat().statusCode(204);




    }
    @Test
    public void testPutWithPATCHMap2() {

        Map<String ,Object> patchMap= new HashMap<>();
        patchMap.put("phone",1234567890L);


        given().contentType(ContentType.JSON)
                .pathParam("id",123)
                .and().body(patchMap)
                .when().patch("/api/spartans/{id}")
                .then().assertThat().statusCode(204);




    }
}