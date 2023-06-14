package com.ti.avaliai.global.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidEmailException extends HttpException{
    public InvalidEmailException(String message, HttpStatus status){
        super(message, status);
    }
}