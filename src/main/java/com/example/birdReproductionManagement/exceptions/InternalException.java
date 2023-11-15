package com.example.birdReproductionManagement.exceptions;

public class InternalException extends RuntimeException{
    private static final long serialVerisionUID = 15;
    public InternalException(String message){
        super(message);
    }
}
