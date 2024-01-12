package com.example.HRM_practice.exception;

public class AccountUnavaliableException extends Exception {
    public AccountUnavaliableException(String message){
        super(message);
    }

    public AccountUnavaliableException(String message, Throwable cause){
        super(message, cause);
    }
}
