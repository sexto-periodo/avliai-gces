package com.ti.avaliai.global.response.error;

import com.ti.avaliai.global.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Builder
public class BaseErrorResponse<T>  extends BaseResponse {
    @NonNull
    protected T object;

    @NonNull
    protected  String error;

    public BaseErrorResponse(int status, String path, String reason, LocalDateTime timestamp, String error){
        super(false, status, path,reason, timestamp);
        this.object = object;
        this.error = error;
    }
}
