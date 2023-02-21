package TestsAPI;

import io.restassured.http.ContentType;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;


public class TestWithPathMethod {

    @BeforeClass
    public void setUpClass() {
        baseURI="http://54.144.70.64:8000";

    }

    @Test
    public void test1() {
        Response response= given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
//        System.out.println("id: "+ response.path("id").toString());
//        System.out.println("name: "+ response.path("name").toString());
//        System.out.println("gender: "+ response.path("gender").toString());
//        System.out.println("phone: "+ response.path("phone").toString());



        int id= response.path("id");
        String name= response.path("name");
        String gender= response.path("gender");
        long phone= response.path("phone");

        System.out.println("id = "+id);
        System.out.println("name = "+name);
        System.out.println("gender = "+gender);
        System.out.println("phone = "+phone);




        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone, 3312820936l);

    }
    @Test
    public void test2() {

            Response response= given().accept(ContentType.JSON)
                          .when().get("/api/spartans/");


            int firstId =response.path("id[0]");
            String firstFName= response.path("name[0]");
            String firstGender=response.path("gender[0]");
            long phone= response.path("phone[0]");

        String lastFname= response.path("name[-1]");

        System.out.println(firstId);
        System.out.println(firstFName);
                System.out.println(firstGender);
        System.out.println(phone);

       System.out.println("Last first name= "+lastFname);


       List<String> names= response.path("name");
        System.out.println(names);
        System.out.println(names.size());
        List<String> ids= response.path("id");
        System.out.println(ids);
        System.out.println(ids.size());
        List<String> genders= response.path("gender");
        System.out.println(genders);
        System.out.println(genders.size());
        List<String> phones= response.path("phone");
        System.out.println(phones);
        System.out.println(phones.size());


    }

}
