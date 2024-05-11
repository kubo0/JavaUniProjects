package com.example.MPR;

import com.example.MPR.classes.Car;
import com.example.MPR.exceptions.CarExceptionHandler;
import com.example.MPR.exceptions.CarNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    private MockMvc mockMvc;
    @Mock
    private CarService carService;
    @InjectMocks
    private CarController carController;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new CarExceptionHandler(), carController).build();
    }

    @Test
    public void getByIdReturns200WhenCarIsPresent() throws Exception {
        Car car = new Car("VW", "Golf", 2020);
        when(carService.findCarById(1L)).thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.get("/car/by-id/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("VW"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Golf"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productionYear").value(2020))
                .andExpect(status().isOk());
    }

    @Test
    public void checkIfCarAddingWorks() throws Exception {
        Car car = new Car("VW", "Golf", 2020);
        mockMvc.perform(post("/car/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"brand\": \"VW\", \"model\": \"Golf\", \"productionYear\": 2020}")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

//    @Test
//    public void templateWithBody() throws Exception {
//        mockMvc.perform(post("/car/add")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content("{\"brand\": \"VW\", \"model\": \"Golf\", \"productionYear\": 2020}")
//                    .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }

    @Test
    public void getByBrandReturns200WhenCarIsPresent() throws Exception {
        Car car1 = new Car("VW", "Golf", 2020);
        Car car2 = new Car("VW", "Passat", 2020);
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        when(carService.findCarByBrand("VW")).thenReturn(carList);

        mockMvc.perform(get("/car/by-brand/VW"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand").value("VW"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value("Golf"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productionYear").value(2020))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].brand").value("VW"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].model").value("Passat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productionYear").value(2020))
                .andExpect(status().isOk());
    }
}
