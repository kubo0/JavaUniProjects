package com.example.MPR;

import com.example.MPR.classes.Car;
import com.example.MPR.exceptions.CarNotFoundException;
import com.example.MPR.exceptions.IdNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarServiceTest {
    @Mock
    private CarRepository repository;
    private AutoCloseable openMocks;
    private CarService carService;

    @BeforeEach
    public void init() {
        openMocks = MockitoAnnotations.openMocks(this);
        carService = new CarService(repository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    public void findCarByIdTest() {
        Long id = 1L;
        String brand = "VW";
        String model = "Golf";
        Car car = new Car(brand, model, 2020);
        when(repository.findCarById(id)).thenReturn(car);

        Car result = carService.findCarById(id);
        assertEquals(car, result);
    }
    @Test
    public void findCarByBrandTest() {
        String brand = "VW";
        String model = "Golf";
        Car car = new Car(brand, model, 2020);
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        when(repository.findCarByBrand(brand)).thenReturn(carList);

        List<Car> result = carService.findCarByBrand(brand);
        assertEquals(carList, result);
    }
    @Test
    public void findCarByModelTest() {
        String brand = "VW";
        String model = "Golf";
        Car car = new Car(brand, model, 2020);
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        when(repository.findCarByModel(model)).thenReturn(carList);

        List<Car> result = carService.findCarByModel(model);
        assertEquals(carList, result);
    }

    @Test
    public void saveTest() {
        String brand = "VW";
        String model = "Golf";
        Car car = new Car(brand, model, 2020);
        ArgumentCaptor<Car> captor = ArgumentCaptor.forClass(Car.class);
        when(repository.save(captor.capture())).thenReturn(car);

        carService.save(car);
        Mockito.verify(repository, Mockito.times(1))
                .save(Mockito.any());
        Car carFromSaveCall = captor.getValue();
        assertEquals(car, carFromSaveCall);
    }
    @Test
    public void deleteCarByIdTest() { // nie działczy
        Long id = 1L;
        String brand = "VW";
        String model = "Golf";
        Car car = new Car(brand, model, 2020);
        car.setId(id);
        carService.save(car);
        assertTrue(carService.existsById(id));
        carService.deleteCarById(id);
        assertFalse(carService.existsById(id));
        Mockito.verify(repository, Mockito.times(1))
                .deleteById(Mockito.any());
    }

    @Test
    public void findCarByIdWhenCarIsNotPresent() {
        Long id = 1L;
        when(repository.findCarById(id)).thenReturn(null);

        assertThrows(CarNotFoundException.class, () -> {
            carService.findCarById(id);
        });
    }
    @Test
    public void findCarByBrandWhenCarIsNotPresent() {
        String brand = "VW";
        List<Car> emptyList = new ArrayList<>();
        when(repository.findCarByBrand(brand)).thenReturn(emptyList);

        assertThrows(CarNotFoundException.class, () -> {
            carService.findCarByBrand(brand);
        });
    }
    @Test
    public void findCarByModelWhenCarIsNotPresent() {
        String model = "Golf";
        List<Car> emptyList = new ArrayList<>();
        when(repository.findCarByModel(model)).thenReturn(emptyList);

        assertThrows(CarNotFoundException.class, () -> {
            carService.findCarByModel(model);
        });
    }
    @Test
    public void deleteCarWhenNoMatchingIdTest() {
        Long id = 2L;

        assertThrows(IdNotFoundException.class, () -> {
            carService.deleteCarById(id);
        });
    }
}
