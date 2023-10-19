package com.example.birdReproductionManagement.exceptions;

public class UserNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 14;
    public UserNotFoundException(String message){
        super(message);
    }
}
