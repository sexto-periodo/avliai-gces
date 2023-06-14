package com.ti.avaliai.global.response.error;

import java.time.LocalDateTime;

public class ErrorResponse422 extends BaseErrorResponse {

    public ErrorResponse422(int status, String path, String reason, LocalDateTime timestamp) {
        super(422, path, reason, timestamp);
    }
}