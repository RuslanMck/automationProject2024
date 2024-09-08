package ui.restassured;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PetDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
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
    @SneakyThrows
    public void createPet(){

        PetDto requestPet = new PetDto().builder()
                .status("available")
                .name("Barsik")
                .build();

        String petId = RestAssured.given()
                .spec(requestSpecification)
                .body(new ObjectMapper().writeValueAsString(requestPet))
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getString("id");
        System.out.println(petId);

        JsonPath responsePetJson = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .get("/pet/" + petId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();

        PetDto responsePet = new ObjectMapper().readValue(responsePetJson.prettify(), PetDto.class);

        Assert.assertEquals(requestPet, responsePet);

    }
}
