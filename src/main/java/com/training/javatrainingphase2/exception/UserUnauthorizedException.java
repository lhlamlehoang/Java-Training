package com.training.javatrainingphase2.exception;

public class UserUnauthorizedException extends RuntimeException{
    public UserUnauthorizedException(String message){
        super(message);
    }
}
