package com.example.birdReproductionManagement.exceptions;

public class FoodNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 7;
    public FoodNotFoundException(String message){
        super(message);
    }
}
