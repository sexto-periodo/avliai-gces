package com.ti.avaliai.global.response.error;

import java.time.LocalDateTime;

public class ErrorResponse403 extends BaseErrorResponse {

    public ErrorResponse403(int status, String path, String reason, LocalDateTime timestamp) {
        super(403, path, reason, timestamp);
    }
}