package com.ti.avaliai.global.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthenticationHttpException extends HttpException{
    public AuthenticationHttpException(String message, HttpStatus httpStatus){
        super(message, httpStatus);
    }
}
