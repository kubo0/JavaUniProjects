package com.example.MPR.exceptions;

public class EmptyCarListException extends RuntimeException{
    public EmptyCarListException() {
        super("Car list is empty! No cars has been added.");
    }
}
