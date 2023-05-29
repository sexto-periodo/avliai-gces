package com.ti.avaliai.global.response.error;

import com.ti.avaliai.global.response.BaseResponse;
import lombok.NonNull;

import java.time.LocalDateTime;

public class BaseErrorResponse<T>  extends BaseResponse {
    @NonNull
    protected T object;

    public BaseErrorResponse(int status, String path, String reason, LocalDateTime timestamp){
        super(false, status, path,reason, timestamp);
        this.object = object;
    }
}
