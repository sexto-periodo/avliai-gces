package com.ti.avaliai.global.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistingEmailException extends HttpException{
    public AlreadyExistingEmailException(String message, HttpStatus status){
        super(message, status);
    }
}
