package com.ti.avaliai.global.domain.exceptions;

import org.springframework.http.HttpStatus;

public class AuthenticationHttpException extends HttpException{
    public AuthenticationHttpException(String message, HttpStatus httpStatus){
        super(message, httpStatus);

    }
}
