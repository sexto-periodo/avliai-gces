package com.ti.avaliai.global.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyReviewedByUserException extends HttpException{
    public AlreadyReviewedByUserException(String message, HttpStatus status){
        super(message, status);
    }
}
