package Serialization;

import DataDriven.JsonReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestSerialization {

    JsonReader jsonReader;

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI = "http://reqres.in/";
        jsonReader = new JsonReader("reqresPostData.json");

    }
    @Test
    public void testSerializationUserArray(){
        Response response = given().when().get("api/users/1");
        Assert.assertEquals(response.statusCode(),200,"Wrong Status code : " + response.statusCode());

//        response.prettyPrint();

        User user = response.body().as(User.class);
        Assert.assertEquals(user.getData().getId(),1);
        Assert.assertEquals(user.getData().getEmail(),"george.bluth@reqres.in");
        Assert.assertEquals(user.getData().getFirstName(),"George");
        Assert.assertEquals(user.getData().getLastName(),"Bluth");
        Assert.assertEquals(user.getData().getAvatar(),"https://reqres.in/img/faces/1-image.jpg");

    }

    @Test
    public void testSerializationAllUsers(){
        Response response = given().when().get("api/users?page=1");
        Assert.assertEquals(response.statusCode(),200,"Wrong Status code : " + response.statusCode());

//        response.prettyPrint();
        Attributes attributes = response.body().as(Attributes.class);
        Assert.assertEquals(attributes.getPage(),1,"Wrong Page Number: " + attributes.getPage());
        Assert.assertEquals(attributes.getPerPage(),6,"Wrong PerPage Number: " + attributes.getPerPage());
        Assert.assertEquals(attributes.getTotal(),12,"Wrong Total Number: " + attributes.getTotal());
        Assert.assertEquals(attributes.getTotalPages(),2,"Wrong TotalPages Number: " + attributes.getTotalPages());

        Assert.assertEquals(attributes.getData().get(0).getId(),1);
        Assert.assertEquals(attributes.getData().get(0).getEmail(),"george.bluth@reqres.in");
        Assert.assertEquals(attributes.getData().get(0).getFirstName(),"George");
        Assert.assertEquals(attributes.getData().get(0).getLastName(),"Bluth");
        Assert.assertEquals(attributes.getData().get(0).getAvatar(),"https://reqres.in/img/faces/1-image.jpg");

    }


    @Test
    public void testSerializationUsers(){

        UserDataPost userDataPost = new UserDataPost();
        userDataPost.setName(jsonReader.readJson("name"));
        userDataPost.setJob(jsonReader.readJson("job"));

        ObjectMapper objectMapper = new ObjectMapper();
        String bodyData = "";
        try {
             bodyData = objectMapper.writeValueAsString(userDataPost);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        Response response = given().contentType(ContentType.JSON)
                .body(bodyData).when().post("api/users");
        Assert.assertEquals(response.statusCode(),201,"Wrong Status code : " + response.statusCode());

        response.prettyPrint();


    }
}
