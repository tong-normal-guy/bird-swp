package com.example.birdReproductionManagement.exceptions;

public class CageTypeNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 4;
    public CageTypeNotFoundException(String message){
        super(message);
    }
}
