package com.example.MPR.exceptions;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException() {
        super("Car not found!");
    }
}
