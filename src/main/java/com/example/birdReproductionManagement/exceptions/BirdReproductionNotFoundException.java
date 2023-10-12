package com.example.birdReproductionManagement.exceptions;

public class BirdReproductionNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 6;
    public BirdReproductionNotFoundException(String message){
        super(message);
    }
}
