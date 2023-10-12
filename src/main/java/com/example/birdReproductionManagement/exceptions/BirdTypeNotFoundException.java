package com.example.birdReproductionManagement.exceptions;

public class BirdTypeNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 3;
    public BirdTypeNotFoundException(String message){
        super(message);
    }
}
