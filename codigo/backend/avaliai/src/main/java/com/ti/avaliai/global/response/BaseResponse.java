package com.ti.avaliai.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Data
@Builder
public class BaseResponse<T> {
    public boolean success;
    public int status;
    public String path;
    public String reason;
    public LocalDateTime timestamp;
    public String error;

    public BaseResponse(boolean success, int status, String path, String reason, LocalDateTime timestamp, String error) {
        this.success = success;
        this.status = status;
        this.path = path;
        this.reason = reason;
        this.timestamp = timestamp;
        this.error = error;
    }
    public BaseResponse(boolean success, int status, String path, String reason, LocalDateTime timestamp) {
        this.success = success;
        this.status = status;
        this.path = path;
        this.reason = reason;
        this.timestamp = timestamp;
        this.error = "";
    }
}
