package com.example.birdReproductionManagement.exceptions;

public class MealNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 8;
    public MealNotFoundException(String message){
        super(message);
    }
}
