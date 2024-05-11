package com.example.MPR;

import com.example.MPR.classes.Car;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("car/all")
    public Iterable<Car> getAllCars() {
        return this.service.getAllCars();
    }

    @GetMapping("car/by-id/{id}")
    public Car getCarByBrand(@PathVariable("id") Long id) {
        return this.service.findCarById(id);
    }

    @GetMapping("car/by-brand/{brand}")
    public List<Car> getCarByBrand(@PathVariable("brand") String brand) {
        return this.service.findCarByBrand(brand);
    }

    @GetMapping("car/by-model/{model}")
    public List<Car> getCarByModel(@PathVariable("model") String model) {
        return this.service.findCarByBrand(model);
    }

//    @GetMapping("car/filter-by-brand/{filter}")
//    public List<Car> getFilteredCarsByBrand(@PathVariable("filter") String filter) {
//        return this.service.filterByBrand(filter);
//    }

    @PostMapping("car/add")
    public void addCar(@RequestBody Car car) {
        this.service.addCar(car);
    }
    //"brand":"Skoda",
    //"model":"Octavia",
    //"productionYear":2002
    @DeleteMapping("car/delete-id/{id}")
    public void deleteCarById(@PathVariable("id") Long id) {
        this.service.deleteCarById(id);
    }

    @DeleteMapping("car/delete-brand/{brand}")
    public void deleteCarByBrand(@PathVariable("brand") String brand) {
        this.service.deleteCarByBrand(brand);
    }

    @PutMapping("car/update-by-id/{id}")
    public void updateCarById(@PathVariable("id") Long id, @RequestBody Car car) {
        service.updateCarById(id, car);
    }
}
