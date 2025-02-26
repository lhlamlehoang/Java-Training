package com.training.javatrainingphase2.exception;

public class BillNotFoundException extends RuntimeException{
    public BillNotFoundException(String message){
        super(message);
    }
}
