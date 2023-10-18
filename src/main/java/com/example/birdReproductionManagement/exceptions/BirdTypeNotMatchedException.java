package com.example.birdReproductionManagement.exceptions;

public class BirdTypeNotMatchedException extends RuntimeException{
    private static final long serialVerisionUID = 11;
    public BirdTypeNotMatchedException(String message){
        super(message);
    }
}
