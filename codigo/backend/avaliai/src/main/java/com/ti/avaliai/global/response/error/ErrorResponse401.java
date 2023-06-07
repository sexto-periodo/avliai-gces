package com.ti.avaliai.global.response.error;

import java.time.LocalDateTime;

public class ErrorResponse401 extends BaseErrorResponse {

    public ErrorResponse401(int status, String path, String reason, LocalDateTime timestamp) {
        super(401, path, reason, timestamp);
    }
}