package com.example.birdReproductionManagement.exceptions;

public class UserEmailExistedException extends RuntimeException{
    private static final long serialVerisionUID = 12;
    public UserEmailExistedException(String message){
        super(message);
    }
}
