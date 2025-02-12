package Runner;

import AutomationBase.Base;
import AutomationBase.Functions;
import AutomationBase.Informations;
import AutomationBase.Report;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;


public class TestRunner extends Base {




    public long petId;
    public long orderId;
    public String name = "Nil";
    public String updatedName = "Nilto";
    public String userName = "omerornek";
    public String updatedUserName = "1omerornek";





    @Test(priority = 1)
    public void addNewPet() {

        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);

        String requestBody = "{ \"id\": 0, \"category\": { \"id\": 0, \"name\": \"string\" }, \"name\": \""+name+"\", \"photoUrls\": [ \"string\" ], \"tags\": [ { \"id\": 0, \"name\": \"string\" } ], \"status\": \"available\" }";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("https://petstore.swagger.io/v2/pet");


        response.then().statusCode(200);


        petId = response.path("id");

    }





    @Test(priority = 2)
    public void addNewPetNegative() {

        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);
        String requestBody = "";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(Informations.baseUrl+"/pet")
                .then()
                .statusCode(405);
    }









    @Test(priority = 3)
    public void uploadPetImage(){
        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);
        File file = new File(Informations.projectDirectory+"cat.jpg");
        String petId2 = petId;

        given()
                .multiPart(file)
                .formParam("additionalMetadata", "TEST")
                .when()
                .post(Informations.baseUrl+"/pet/" + petId2 + "/uploadImage")
                .then()
                .statusCode(200);
    }






    @Test(priority = 4)
    public void uploadPetImageNegative(){
        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);
        File file = new File(Informations.projectDirectory+"cat.jpg");
        String petId2 = "petId+22";

        given()
                .multiPart(file)
                .formParam("additionalMetadata", "TEST")
                .when()
                .post(Informations.baseUrl+"/pet/" + petId2 + "/uploadImage")
                .then()
                .statusCode(404);
    }



    @Test(priority = 5)
    public void updatePet() {

        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);

        String requestBody = "{ \"id\": 0, \"category\": { \"id\": 0, \"name\": \"string\" }, \"name\": \""+updatedName+"\", \"photoUrls\": [ \"string\" ], \"tags\": [ { \"id\": 0, \"name\": \"string\" } ], \"status\": \"available\" }";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(Informations.baseUrl+"/pet");


        response.then().statusCode(200);


        petId = response.path("id");

    }





    @Test(priority = 6)
    public void updatePetNegative() {

        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);
        String requestBody = "";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(Informations.baseUrl+"/pet")
                .then()
                .statusCode(405);
    }


    @Test(priority = 7)
    public void findPetsByStatus() {
        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);
        given()
                .contentType(ContentType.JSON)
                .queryParam("status", "available")
                .when()
                .get(Informations.baseUrl+"/pet/findByStatus")
                .then()
                .statusCode(200);
    }



    @Test(priority = 8)
    public void findPetsByStatusNegative() {
        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);
        given()
                .contentType(ContentType.JSON)
                .queryParam("status", 123)
                .when()
                .get(Informations.baseUrl+"/pet/findByStatus")
                .then()
                .statusCode(400);
    }



    @Test(priority = 9)
    public void FindPetById() {
        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(Informations.baseUrl+"/pet/"+petId);



        response.then().statusCode(200);

        String nameSearched = response.jsonPath().getString("name");

       Assert.assertEquals(nameSearched,updatedName);

    }



    @Test(priority = 10)
    public void FindPetByIdNegative() {
        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(Informations.baseUrl+"/pet/"+"abc");



        response.then().statusCode(404);



    }

     @Test(priority = 11)
    public void DeletePet() {
        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);

        Response response = given()
                .headers("api_key",Informations.api_key)
                .accept(ContentType.JSON)
                .when()
                .delete(Informations.baseUrl+"/pet/"+pet2);



        response.then().statusCode(200);



    }



































}

