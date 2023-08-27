package Reqres;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ReqresTest {

    @Test
    public void test1(){
        int pageNo = 1;
        RestAssured.baseURI= "http://reqres.in";
        Response res1 =
                given().queryParam("page",pageNo)
                .when()
                .get("/api/users");
        res1.prettyPrint();
        Assert.assertEquals(res1.statusCode(),200,"response status code is not 200");
        Assert.assertEquals((Integer) res1.body().jsonPath().get("page"),pageNo,"msg");
    }
    @Test
    public void test2(){
        Response res1 =
                given().queryParam("page",1)
                        .when()
                        .get("/api/users");
        System.out.println();

    }
}
