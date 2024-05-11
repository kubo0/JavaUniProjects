package com.example.MPR;

import com.example.MPR.classes.Car;
import com.example.MPR.exceptions.CarNotFoundException;
import com.example.MPR.exceptions.IdNotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.MockServerRestClientCustomizer;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.method;

@RestClientTest
public class CarServiceTest {
    MockServerRestClientCustomizer customizer = new MockServerRestClientCustomizer();
    RestClient.Builder builder = RestClient.builder();
    CarService carService;

    @BeforeEach
    public void init() {
        customizer.customize(builder);
        carService = new CarService(builder.build());
    }

    @Test
    public void findCarByIdTest() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8080/car/by-id/1"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                                {
                                        "id": 1,
                                        "brand": "Audi",
                                        "model": "A3",
                                        "productionYear": 2022
                                    }"""
                , MediaType.APPLICATION_JSON));

        Car car = carService.findCarById(1L);
        assertEquals(1L, car.getId());
    }
    @Test
    public void findCarByBrandTest() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8080/car/by-brand/Audi"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                                [
                                    {
                                        "id": 1,
                                        "brand": "Audi",
                                        "model": "A3",
                                        "productionYear": 2022
                                    }
                                ]"""
                        , MediaType.APPLICATION_JSON));

        List<Car> car = carService.findCarByBrand("Audi");
        assertEquals("Audi", car.get(0).getBrand());
    }
    @Test
    public void findCarByModelTest() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8080/car/by-model/A3"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                                [
                                    {
                                        "id": 1,
                                        "brand": "Audi",
                                        "model": "A3",
                                        "productionYear": 2022
                                    }
                                ]"""
                        , MediaType.APPLICATION_JSON));

        List<Car> car = carService.findCarByModel("A3");
        assertEquals("A3", car.get(0).getModel());
    }

    @Test
    public void saveTest() {
        Car carToSave = new Car( "VW", "Golf", 2023);

        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8080/car/add"))
                .andExpect(MockRestRequestMatchers.jsonPath("$.brand", Matchers.equalTo(carToSave.getBrand())))
                .andExpect(MockRestRequestMatchers.jsonPath("$.model", Matchers.equalTo(carToSave.getModel())))
                .andExpect(MockRestRequestMatchers.jsonPath("$.productionYear", Matchers.equalTo(carToSave.getProductionYear())))
                .andRespond(MockRestResponseCreators.withSuccess());

        carService.save(carToSave);
    }
    @Test
    public void deleteCarByIdTest() {
        customizer.getServer().expect(MockRestRequestMatchers.requestTo("http://localhost:8080/car/delete-id/1"))
                .andRespond(MockRestResponseCreators.withSuccess());
    }

//    @Test
//    public void findCarByIdWhenCarIsNotPresent() {
//
//        Long id = 1L;
//        when(repository.findCarById(id)).thenReturn(null);
//
//        assertThrows(CarNotFoundException.class, () -> {
//            carService.findCarById(id);
//        });
//    }
//    @Test
//    public void findCarByBrandWhenCarIsNotPresent() {
//
//        String brand = "VW";
//        List<Car> emptyList = new ArrayList<>();
//        when(repository.findCarByBrand(brand)).thenReturn(emptyList);
//
//        assertThrows(CarNotFoundException.class, () -> {
//            carService.findCarByBrand(brand);
//        });
//    }
//    @Test
//    public void findCarByModelWhenCarIsNotPresent() {
//
//        String model = "Golf";
//        List<Car> emptyList = new ArrayList<>();
//        when(repository.findCarByModel(model)).thenReturn(emptyList);
//
//        assertThrows(CarNotFoundException.class, () -> {
//            carService.findCarByModel(model);
//        });
//    }
//    @Test
//    public void deleteCarWhenNoMatchingIdTest() {
//
//        Long id = 2L;
//        when(repository.deleteById(id)).thenThrow(new IdNotFoundException());
//    }
}
