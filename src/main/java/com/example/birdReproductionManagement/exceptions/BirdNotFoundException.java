package com.example.birdReproductionManagement.exceptions;

public class BirdNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 1;
    public BirdNotFoundException(String message){
        super(message);
    }
}
