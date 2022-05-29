package APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestWithHamcrest {
    @BeforeClass
    public void setupClass() {

        RestAssured.baseURI = "http://18.234.174.132:8000";

    }
    /*
       Given accept type is json
       and path param is 15
       When user sends get request to "/api/spartans/{id}"
         Then status code is 200
         And content type is "application/json"
         And response payload values match the following:
               id is 15,
               name is "Meta"
               gender is "Female"
               phone is "1938695106"
     */
@Test
    public void test1(){
        RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).and()
                .assertThat().contentType("application/json");
    }

    @Test
    public void test2(){
        RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", Matchers.equalTo(15),
                        "name",Matchers.equalTo("Meta"),
                         "gender", Matchers.equalTo("Female"),
                         "phone", equalTo(1938695106)

                );

    }
}
