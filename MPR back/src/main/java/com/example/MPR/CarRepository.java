package com.example.MPR;

import com.example.MPR.classes.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    Car findCarById(Long id);
    List<Car> findCarByBrand(String brand);
    List<Car> findCarByModel(String model);
    void deleteByBrand(String brand);
    void deleteById(Long id);
    boolean existsById(Long id);
}
