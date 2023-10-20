package com.example.birdReproductionManagement.exceptions;

public class UsernameExistedException extends RuntimeException{
    private static final long serialVerisionUID = 13;
    public UsernameExistedException(String message){
        super(message);
    }
}
