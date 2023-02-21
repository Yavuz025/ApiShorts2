package TestsAPI;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestWithJsonPath {
    @BeforeClass
    public void setupClass() {

        baseURI="http://54.144.70.64:8000";

    }
    /*
          Given accept type is json
          and path param is 11
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
    public void test1() {
        Response response= given().accept(ContentType.JSON)
                .and().pathParam("id",11)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        int id = response.path("id");
        System.out.println("id = " + id);

        

        JsonPath jsonData= response.jsonPath();

        int id1 = jsonData.getInt("id");
        System.out.println("id1 = " + id1);

        String  name1 = jsonData.get("name");
        System.out.println("name1 = " + name1);

        Object gender = jsonData.get("gender");
        System.out.println("gender = " + gender);

        long phone = jsonData.getLong("phone");
        System.out.println("phone = " + phone);


        assertEquals(id1,11);
        assertEquals(name1,"Nona");
        assertEquals(gender,"Female");
        assertEquals(phone,7959094216l);

    }
}
