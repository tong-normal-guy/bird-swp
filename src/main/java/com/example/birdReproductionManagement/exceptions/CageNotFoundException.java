package com.example.birdReproductionManagement.exceptions;

public class CageNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 3;
    public CageNotFoundException(String message){
        super(message);
    }
}
