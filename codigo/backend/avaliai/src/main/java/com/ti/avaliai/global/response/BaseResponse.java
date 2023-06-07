package com.ti.avaliai.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Data
@Builder
public class BaseResponse<T> {


    public boolean sucsses;
    public int status;
    public String path;
    public String reason;
    public LocalDateTime timestamp;

    public BaseResponse(boolean sucsses, int status, String path, String reason, LocalDateTime timestamp) {
        this.sucsses = sucsses;
        this.status = status;
        this.path = path;
        this.reason = reason;
        this.timestamp = timestamp;
    }
}
