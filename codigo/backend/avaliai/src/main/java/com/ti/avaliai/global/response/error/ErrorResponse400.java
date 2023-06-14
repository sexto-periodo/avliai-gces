package com.ti.avaliai.global.response.error;

import java.time.LocalDateTime;

public class ErrorResponse400 extends BaseErrorResponse {

    public ErrorResponse400(int status, String path, String reason, LocalDateTime timestamp) {
        super(400, path, reason, timestamp);
    }
}