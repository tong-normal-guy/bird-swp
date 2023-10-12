package com.example.birdReproductionManagement.exceptions;

public class WorkDivisionNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 10;
    public WorkDivisionNotFoundException(String message){
        super(message);
    }
}
