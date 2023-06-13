package com.ti.avaliai.global.response.error;

import java.time.LocalDateTime;

public class ErrorResponse409 extends BaseErrorResponse {

    public ErrorResponse409(int status, String path, String reason, LocalDateTime timestamp) {
        super(409, path, reason, timestamp);
    }
}