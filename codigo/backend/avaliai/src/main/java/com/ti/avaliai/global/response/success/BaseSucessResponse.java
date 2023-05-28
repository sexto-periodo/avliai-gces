package com.ti.avaliai.global.response.success;

import com.ti.avaliai.global.response.BaseResponse;
import lombok.NonNull;

import java.time.LocalDateTime;

public class BaseSucessResponse<T> extends BaseResponse {

    @NonNull
    protected T object;

    public BaseSucessResponse(int status, String path, String reason, LocalDateTime timestamp){
        super(true, status, path,reason, timestamp);
        this.object = object;
    }
}
