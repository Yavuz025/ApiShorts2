package TestsAPI;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class TestsPOJODeserialization {

    @BeforeClass
    public void setupClass() {
        baseURI="http://54.144.70.64:8000";

    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");
//                .then().assertThat().statusCode(200)
//                .and().assertThat().contentType("application/json");

        Spartan spartan1 = response.body().as(Spartan.class);
        System.out.println( spartan1.toString());

        assertEquals(spartan1.getId(),15);
        assertEquals(spartan1.getName(),"Meta");
        assertEquals(spartan1.getGender(),"Female");
        assertEquals(spartan1.getPhone(1234567890L),new Long(1938695106));




    }

    @Test
    public void gsonExample() {
        Gson gson=new Gson();

        String myJsonBody="{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        Spartan spartanMeta = gson.fromJson(myJsonBody, Spartan.class);

        System.out.println( spartanMeta.toString());




        Spartan spartan= new Spartan(105, "Yavuz", "Male", 3232441l);

        String jsonBody= gson.toJson(spartan);
        System.out.println( jsonBody);

    }
}
