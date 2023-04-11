package com.ti.avaliai.global.response;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

public class BaseSucessResponse<T> extends  BaseResponse {

    @NonNull
    protected T object;

    public BaseSucessResponse(int status, String path, String reason, LocalDateTime timestamp){
        super(true, status, path,reason, timestamp);
        this.object = object;
    }
}
