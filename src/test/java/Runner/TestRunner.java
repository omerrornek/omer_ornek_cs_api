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




    public String petId;
    public String name = "Nil";
    public String updatedName = "Nilto";



    @Test(priority = 1)
    public void addNewPet() {

        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);

        String requestBody = "{ \"id\": 0, \"category\": { \"id\": 0, \"name\": \"string\" }, \"name\": \""+name+"\", \"photoUrls\": [ \"string\" ], \"tags\": [ { \"id\": 0, \"name\": \"string\" } ], \"status\": \"available\" }";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("https://petstore.swagger.io/v2/pet");


        response.then().statusCode(200);


        petId = response.path("id").toString();

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

        given()
                .multiPart(file)
                .formParam("additionalMetadata", "TEST")
                .when()
                .post(Informations.baseUrl+"/pet/" + petId + "/uploadImage")
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


        petId = response.path("id").toString();

    }






    @Test(priority = 6)
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




    @Test(priority = 7)
    public void FindPetById() {
        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(Informations.baseUrl+"/pet/"+petId);



        response.then().statusCode(200);

    }



     @Test(priority = 8)
    public void deletePet() {
        Functions.reportTestCase(Informations.author,Informations.apiTestCategory);

        Response response = given()
                .headers("api_key",Informations.api_key)
                .accept(ContentType.JSON)
                .when()
                .delete(Informations.baseUrl+"/pet/"+petId);



        response.then().statusCode(200);



    }



































}

