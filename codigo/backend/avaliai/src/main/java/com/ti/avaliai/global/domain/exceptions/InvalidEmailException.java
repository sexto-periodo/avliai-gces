package com.ti.avaliai.global.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends ApiException{
    public InvalidEmailException(String message){
        super(message);
    }
}
