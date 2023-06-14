package com.ti.avaliai.global.domain.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedReviewerException extends UnauthorizedException {

    public UnauthorizedReviewerException(
            String message, HttpStatus status) {
        super(message, status);
    }
}
