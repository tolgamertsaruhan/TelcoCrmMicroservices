package com.etiya.common.crosscuttingconcerns.exceptions.types;

public class AuthException extends RuntimeException{
    public AuthException(String message){
        super(message);
    }
}
