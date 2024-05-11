package com.example.MPR;

import com.example.MPR.classes.Car;
import com.example.MPR.exceptions.CarNotFoundException;
import com.example.MPR.exceptions.EmptyCarListException;
import com.example.MPR.exceptions.IdNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarService {
    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
//        this.repository.save(new Car("Audi", "A3", 2022));
//        this.repository.save(new Car("BMW", "Seria 3", 2012));
    }

    public void save(Car car) {
        this.repository.save(car);
    }

    public Iterable<Car> getAllCars() {
        Iterable<Car> allCars = this.repository.findAll();

        return allCars;
    }

    public Car findCarById(Long id) {
        Car repoCar = this.repository.findCarById(id);

        if (repoCar != null)
            return repoCar;
        else throw new CarNotFoundException();
    }

    public List<Car> findCarByBrand(String brand) {
        List<Car> repoCarsList = this.repository.findCarByBrand(brand);

        if (!repoCarsList.isEmpty())
            return repoCarsList;
        else throw new CarNotFoundException();
    }

    public List<Car> findCarByModel(String model) {
        List<Car> repoCarsList = this.repository.findCarByModel(model);

        if (!repoCarsList.isEmpty())
            return repoCarsList;
        else throw new CarNotFoundException();
    }

    public void addCar(Car car) {
        this.repository.save(car);
    }

    @Transactional
    public void deleteCarByBrand(String brand) {
        this.repository.deleteByBrand(brand);
    }

    public void deleteCarById(Long id) {
        if (this.repository.findCarById(id) != null)
            this.repository.deleteById(id);
        else throw new IdNotFoundException();
    }

    public void updateCarById(Long id, Car car) {
        Car existingCar = findCarById(id);

        if (existingCar != null) {
            if (car.getBrand() != null)
                existingCar.setBrand(car.getBrand());
            if (car.getModel() != null)
                existingCar.setModel(car.getModel());
            if (car.getProductionYear() != 0)
                existingCar.setProductionYear(car.getProductionYear());

            save(existingCar);
        } else throw new CarNotFoundException();
    }

    public boolean existsById(Long id) {
        Car tempCar = findCarById(id);

        if (tempCar != null)
            return true;
        else return false;
    }
}
