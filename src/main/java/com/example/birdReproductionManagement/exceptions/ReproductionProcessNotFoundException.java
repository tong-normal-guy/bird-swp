package com.example.birdReproductionManagement.exceptions;

public class ReproductionProcessNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 9;
    public ReproductionProcessNotFoundException(String message){
        super(message);
    }
}
