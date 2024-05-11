package com.example.MPR;

import com.example.MPR.classes.Car;
import com.example.MPR.exceptions.CarNotFoundException;
import com.example.MPR.exceptions.EmptyCarListException;
import com.example.MPR.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Iterator;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private final RestClient restClient;
    private final String URI = "http://localhost:8080";

    public CarService(RestClient restClient) {
        this.restClient = restClient;
    }

    public void save(Car car) {
        this.restClient
                .post()
                .uri(URI + "/car/add")
                .body(car)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();
    }

    public Iterable<Car> getAllCars() {
        ResponseEntity<Iterable<Car>> allCarsResponse = this.restClient
                .get()
                .uri(URI + "/car/all")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Iterable<Car>>() {});

        Iterable<Car> allCars = allCarsResponse.getBody();

        return allCars;
    }

    public Car findCarById(Long id) {
         ResponseEntity<Car> repoCar = this.restClient
                 .get()
                 .uri(URI + "/car/by-id/" + id)
                 .retrieve()
                 .toEntity(Car.class);

        if (repoCar != null) {
            return repoCar.getBody();
        } else throw new CarNotFoundException();
    }

    public List<Car> findCarByBrand(String brand) {
        ResponseEntity<List<Car>> repoCarsListResponse = this.restClient
                .get()
                .uri(URI + "/car/by-brand/" + brand)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Car>>() {});

        List<Car> repoCarsList = repoCarsListResponse.getBody();

        if (!repoCarsList.isEmpty()) {
            return repoCarsList;
        } else throw new CarNotFoundException();
    }

    public List<Car> findCarByModel(String model) {
        ResponseEntity<List<Car>> repoCarsListResponse = this.restClient
                .get()
                .uri(URI + "/car/by-model/" + model)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Car>>() {});

        List<Car> repoCarsList = repoCarsListResponse.getBody();

        if (!repoCarsList.isEmpty()) {
            return repoCarsList;
        } else throw new CarNotFoundException();
    }

    public void deleteCarByBrand(String brand) {
        this.restClient
                .delete()
                .uri(URI + "/car/delete-brand/" + brand)
                .retrieve()
                .toBodilessEntity();
    }

    public void deleteCarById(Long id) {
        if (findCarById(id) != null) {
            this.restClient
                    .delete()
                    .uri(URI + "/car/delete-id/" + id)
                    .retrieve()
                    .toBodilessEntity();
        } else throw new IdNotFoundException();
    }

    public void updateCarById(Long id, Car car) {
        Car existingCar = findCarById(id);

        if (existingCar != null) {
            if (car.getBrand() != null && !car.getBrand().isEmpty())
                existingCar.setBrand(car.getBrand());
            if (car.getModel() != null && !car.getModel().isEmpty())
                existingCar.setModel(car.getModel());
            if (car.getProductionYear() != 0)
                existingCar.setProductionYear(car.getProductionYear());

            save(existingCar);
        } else throw new CarNotFoundException();
    }
}
