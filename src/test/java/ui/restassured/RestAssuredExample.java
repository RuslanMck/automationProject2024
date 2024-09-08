package ui.restassured;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.OrderDto;
import dto.PetDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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

//        Assert.assertEquals(requestPet, responsePet);

        Assertions.assertThat(requestPet)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(responsePet);

        System.out.println(responsePet.getName() + " " + responsePet.getStatus()+ " "  + responsePet.getId());

        OrderDto orderDto = new OrderDto().builder()
                .id(12)
                .petId(responsePet.getId())
                .quantity(1)
                .status("placed")
                .complete(true)
                .build();

        JsonPath responseOrderJson = RestAssured.given()
                .spec(requestSpecification)
                .body(new ObjectMapper().writeValueAsString(orderDto))
                .when()
                .post("/store/order")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();

        OrderDto responseOrder = new ObjectMapper().readValue(responseOrderJson.prettify(), OrderDto.class);

        Assert.assertEquals(responseOrder, orderDto);

        JsonPath createdOrderJson = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .get("/store/order/" + responseOrder.getId())
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();

        OrderDto createdOrder = new ObjectMapper().readValue(createdOrderJson.prettify(), OrderDto.class);

         Assert.assertEquals(createdOrder, orderDto);
    }

    @Test
    public void collectionTest(){
        List<String> actualCollection = new ArrayList<>();
        actualCollection = List.of("one", "two", "three");

        List<String> expectedCollection = new ArrayList<>();
        expectedCollection = List.of("two", "one", "three");

        Assertions.assertThat(actualCollection)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedCollection);
    }
}
