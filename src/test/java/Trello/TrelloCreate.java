package Trello;

import DataDriven.JsonReader;
import Interfaces.Configurations;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;


public class TrelloCreate {

//    public static final String key = "";
//    public static final String token = "";
    private static Logger log;

    Configurations configurations = ConfigFactory.create(Configurations.class);

    JsonReader jsonReader;
    private final String JSON_FILE = configurations.jsonFileName();


    @BeforeMethod
    public void SetUp(){
        RestAssured.baseURI = configurations.baseUrl();
        jsonReader = new JsonReader(JSON_FILE);
        log = LogManager.getLogger(TrelloCreate.class.getSimpleName());
    }


    @Test
    public void createBoard(ITestContext contextBoard){
        String idBoard =
            given().
                    contentType(ContentType.JSON)
                    .with()
                    .queryParam("name",jsonReader.readJson("BoardName"))
                    .queryParam("key",jsonReader.readJson("Key"))
                    .queryParam("token",jsonReader.readJson("Token"))
                    .when()
                    .post("/1/boards/")
                    .jsonPath().getString("id");

        log.info("Generated Board id is < " + idBoard + " >");
        contextBoard.setAttribute("boardId", idBoard);
    }
    @Test(dependsOnMethods = "createBoard")
    public void createList(ITestContext contextBoard,ITestContext contextList){

        String idBoard = (String) contextBoard.getAttribute("boardId");
        String idList =

            given().
                    contentType(ContentType.JSON)
                    .with()
                    .queryParam("name",jsonReader.readJson("ListName"))
                    .queryParam("key",jsonReader.readJson("Key"))
                    .queryParam("token",jsonReader.readJson("Token"))
                    .queryParam("idBoard", idBoard)
                    .when()
                    .post("/1/lists/")
                    .jsonPath().getString("id");

        log.info("Generated List id is < " + idList + " >");
        contextList.setAttribute("listId", idList);
    }
    @Test(dependsOnMethods = "createList")
    public void createCard(ITestContext contextList,ITestContext contextCard){
        String idList = (String) contextList.getAttribute("listId");
        String idCard =

            given().
                    contentType(ContentType.JSON)
                    .with()
                    .queryParam("name",jsonReader.readJson("CardName"))
                    .queryParam("key",jsonReader.readJson("Key"))
                    .queryParam("token",jsonReader.readJson("Token"))
                    .queryParam("idList",idList)
                    .when()
                    .post("/1/cards/")
                    .jsonPath().getString("id");

        log.info("Generated Card id is < " + idCard + " >");
        contextCard.setAttribute("cardId", idCard);
    }
    @Test(dependsOnMethods = "createCard")
    public void createChecklist(ITestContext contextCard,ITestContext contextChecklist){
        String idCard = (String) contextCard.getAttribute("cardId");
        String idChecklist =

            given().
                    contentType(ContentType.JSON)
                    .with()
                    .queryParam("name",jsonReader.readJson("ChecklistName"))
                    .queryParam("key",jsonReader.readJson("Key"))
                    .queryParam("token",jsonReader.readJson("Token"))
                    .queryParam("idCard",idCard)
                    .when()
                    .post("/1/checklists/")
                    .jsonPath().getString("id");

        log.info("Generated Checklist id is < " + idChecklist + " >");
        contextChecklist.setAttribute("ChecklistId", idChecklist);
    }
}
