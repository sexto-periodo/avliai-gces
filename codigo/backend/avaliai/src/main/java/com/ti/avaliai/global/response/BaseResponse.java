package com.ti.avaliai.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
public class BaseResponse<T> {


    public boolean sucsses;
    public int status;
    public String path;
    public String reason;
    public LocalDateTime timestamp;

}
