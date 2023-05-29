package com.ti.avaliai.global.response.error;

import java.time.LocalDateTime;

public class ErrorResponse404 extends BaseErrorResponse {

    public ErrorResponse404(int status, String path, String reason, LocalDateTime timestamp) {
        super(404, path, reason, timestamp);
    }
}