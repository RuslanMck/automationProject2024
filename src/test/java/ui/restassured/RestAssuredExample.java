package ui.restassured;

import dto.PetDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class RestAssuredExample {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private RequestSpecification requestSpecification;


    @BeforeClass
    public void setup() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build();
    }

    @Test
    public void createPet(){

        String petId = RestAssured.given()
                .spec(requestSpecification)
                .body(new File("src/test/resources/testdata/petstore/barsik.json"))
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getString("id");
        System.out.println(petId);

        String petName = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .get("/pet/" + petId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getString("name");
        System.out.println(petName);

        PetDto petDto = new PetDto();
        petDto.
    }
}
