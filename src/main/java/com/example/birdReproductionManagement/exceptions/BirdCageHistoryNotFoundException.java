package com.example.birdReproductionManagement.exceptions;

public class BirdCageHistoryNotFoundException extends RuntimeException{
    private static final long serialVerisionUID = 5;
    public BirdCageHistoryNotFoundException(String message){
        super(message);
    }
}
