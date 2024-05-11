package com.example.MPR;

import com.example.MPR.classes.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    @GetMapping(value = "/welcome")
    public String getWelcomeView() {
        return "welcome";
    }

    private final CarService service;

    @Autowired
    public WebController(CarService carService) {
        this.service = carService;
    }

    @GetMapping(value = "/index")
    public String getIndexView(Model model) {
        model.addAttribute("cars", service.getAllCars());
        return "index";
    }

    @GetMapping(value = "/addCar")
    public String getAddView(Model model) {
        model.addAttribute("car", new Car("", "", 0));
        return "addCar";
    }
    @PostMapping(value = "/addCar")
    public String addCar(Car car, Model model, RedirectAttributes redirectAttributes) {
        if (car.getBrand().equals("") || car.getModel().equals("") || car.getProductionYear() < 0) {
            model.addAttribute("errorMessage", "Some data are incorect!");
            return "addCar";
        }
        service.save(car);
        redirectAttributes.addFlashAttribute("successMessage", "Car added");
        return "redirect:/index";
    }

    @GetMapping(value = "/editCar/{id}")
    public String getEditCar(@PathVariable("id") long id, Model model) {
        Car car = service.findCarById(id);
        model.addAttribute("car", car);
        return "editCar";
    }
    @PostMapping(value = "/editCar")
    public String editCar(Car car, RedirectAttributes redirectAttributes) {
        service.updateCarById(car.getId(), car);
        redirectAttributes.addFlashAttribute("successMessage", "Car updated");
        return "redirect:/index";
    }

    @GetMapping(value = "/deleteCar/{id}")
    public String deleteCar(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        service.deleteCarById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Car deleted");
        return "redirect:/index";
    }
}
