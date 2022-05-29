package APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestWithJsonPath {



        @BeforeClass
        public void setupClass() {

            RestAssured.baseURI = "http://18.234.174.132:8000";

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
            Response response = RestAssured.given().accept(ContentType.JSON)
                    .and().pathParam("id", 11)
                    .when().get("/api/spartans/{id}");

            //how to read value with path method
            int id = response.path("id");
            System.out.println(id);

            //how to read value with JsonPath

            JsonPath jsonData = response.jsonPath();
            System.out.println("jsonData.getInt(\"id\") = " + jsonData.getInt("id"));

            String name = jsonData.getString("name");
            String gender = jsonData.getString("gender");
            Long phone= jsonData.getLong("phone");

            System.out.println("name = " + name);
            System.out.println("gender = " + gender);
            System.out.println("phone = " + phone);

            Assert.assertEquals(name,"Noan");

            //then you can assert others similarly


        }


}
