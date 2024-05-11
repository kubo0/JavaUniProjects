package com.example.MPR.exceptions;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException() {
        super("Could not find any Car with this ID!");
    }
}
