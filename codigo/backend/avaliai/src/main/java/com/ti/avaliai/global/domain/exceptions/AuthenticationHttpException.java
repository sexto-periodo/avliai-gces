package com.ti.avaliai.global.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AuthenticationHttpException extends ForbbidenException{
    public AuthenticationHttpException(String message, HttpStatus httpStatus){
        super(message, httpStatus);
    }
}
