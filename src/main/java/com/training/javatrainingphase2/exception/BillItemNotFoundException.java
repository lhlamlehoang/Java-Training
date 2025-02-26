package com.training.javatrainingphase2.exception;

public class BillItemNotFoundException extends RuntimeException{
    public BillItemNotFoundException(String message){
        super(message);
    }
}
