package APITests;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class SpartanPOSTRequests {
    @BeforeClass
    public void setupClass() {

        RestAssured.baseURI = "http://18.234.174.132:8000";

    }

    @Test
    public void postWithString(){
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"gender\": \"Male\",\n" +
                        "  \"name\": \"Mike\",\n" +
                        "  \"phone\": 8877777777\n" +
                        "}")
                .when().post("/api/spartans");

        //System.out.println(response.prettyPrint());

        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertEquals(response.statusCode(), 201);

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.name"), "Mike");

    }

    @Test
    public void PostMethodWithMap(){
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put ("name", "Mike");
        requestMap.put ("gender", "Male");
        requestMap.put ("phone", "8777777777");

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("/api/spartans/");

        Assert.assertEquals(response.statusCode(),201);

        response.prettyPrint();

    }

    @Test
    public void postWithPojo(){
        Spartan spartan = new Spartan();
        spartan.setName("MikePOJO");
        spartan.setGender("Male");
        spartan.setPhone(2222222222l);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(spartan)
                .when().post("/api/spartans/");

        Assert.assertEquals(response.statusCode(),201);

        response.prettyPrint();

        Response response2 = given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when().get("api/spartans/{id}");

        Spartan spartanResponse = response2.body().as(Spartan.class);
        System.out.println(spartanResponse.toString());
    }

}
