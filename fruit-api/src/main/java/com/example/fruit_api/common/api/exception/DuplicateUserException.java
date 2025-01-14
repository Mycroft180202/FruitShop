package com.example.fruit_api.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateUserException extends RuntimeException{
    public DuplicateUserException(String message){
        super(message);
    }
}
