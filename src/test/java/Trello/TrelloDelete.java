package Trello;

import DataDriven.JsonReader;
import Interfaces.Configurations;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class TrelloDelete {


    Configurations configurations = ConfigFactory.create(Configurations.class);

    JsonReader jsonReader;
    JsonReader jsonReaderTest;
    private final String CONFIGURATION_JSON_FILE = configurations.jsonFileName();
    private static Logger log;


    @BeforeMethod
    public void SetUp(){
        RestAssured.baseURI = configurations.baseUrl();
        jsonReader = new JsonReader(CONFIGURATION_JSON_FILE);
        jsonReaderTest = new JsonReader("TestData.json");
        log = LogManager.getLogger(TrelloDelete.class.getSimpleName());
    }

    @Test
    public void deleteChecklist(ITestContext contextChecklist){


//        String idChecklist = (String) contextChecklist.getAttribute("ChecklistId");
        String idChecklist = jsonReaderTest.readJson("ChecklistId");

        given().
                contentType(ContentType.JSON)
                .with()
                .queryParam("key", jsonReader.readJson("Key"))
                .queryParam("token", jsonReader.readJson("Token"))
                .pathParam("idChecklist",idChecklist)
                .when()
                .delete("/1/checklists/{idChecklist}");
        log.info("Deleted Checklist with id < " + idChecklist + " >");
    }
    @Test(dependsOnMethods = "deleteChecklist")
    public void deleteCard(ITestContext contextCard){
//        String idCard = (String) contextCard.getAttribute("cardId");
        String idCard = jsonReaderTest.readJson("cardId");

        given().
                contentType(ContentType.JSON)
                .with()
                .queryParam("key",jsonReader.readJson("Key"))
                .queryParam("token",jsonReader.readJson("Token"))
                .pathParam("idCard",idCard)
                .when()
                .delete("/1/cards/{idCard}");

        log.info("Deleted Card with id < " + idCard + " >");

    }
    @Test(dependsOnMethods = "deleteCard")
    public void deleteList(ITestContext contextList){
//        String idList = (String) contextList.getAttribute("listId");
        String idList = jsonReaderTest.readJson("listId");

        given().
                contentType(ContentType.JSON)
                .with()
                .queryParam("key",jsonReader.readJson("Key"))
                .queryParam("token",jsonReader.readJson("Token"))
                .pathParam("idList",idList)
                .when()
                .delete("/1/lists/{idList}");

        log.info("Deleted List with id < " + idList + " >");

    }
    @Test(dependsOnMethods = "deleteList")
    public void deleteBoard(ITestContext contextBoard){
//        String idBoard = (String) contextBoard.getAttribute("boardId");
        String idBoard = jsonReaderTest.readJson("boardId");

        given().
                contentType(ContentType.JSON)
                .with()
                .queryParam("key",jsonReader.readJson("Key"))
                .queryParam("token",jsonReader.readJson("Token"))
                .pathParam("idBoard",idBoard)
                .when()
                .delete("/1/boards/{idBoard}");

        log.info("Deleted Board with id < " + idBoard + " >");

    }
}
