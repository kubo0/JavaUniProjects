package com.example.MPR;

import com.example.MPR.classes.Car;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyRestAssuredControllerTest {
    private static final String URI = "http://localhost:8080";

    @Test
    public void testFindCarById() {
        when()
                .get(URI + "/car/by-id/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1))
                .log()
                .body();
    }

    @Test
    public void testFindCarById2() {
        Car car = when()
                .get(URI + "/car/by-id/1")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .as(Car.class);

        assertEquals(1, car.getId());
    }

    @Test
    public void testAddCar() {
        with()
                .body(new Car("VW", "Golf", 2020))
                .contentType("application/json")
                .when()
                .post(URI + "/car/add")
                .then()
                .statusCode(200)
                .assertThat()
                .body("brand", equalTo("VW"))
                .log()
                .body();
    }

    @Test
    public void testDeleteCarById() {
        when()
                .delete(URI + "/car/delete-id/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testUpdateCarById() {
        with()
                .body(new Car("Skoda", "Superb", 2002))
                .contentType("application/json")
                .when()
                .put(URI + "/car/update-by-id/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("brand", equalTo("Skoda"))
                .body("model", equalTo("Superb"))
                .body("productionYear", equalTo(2002))
                .log()
                .body()
                ;
    }
}
