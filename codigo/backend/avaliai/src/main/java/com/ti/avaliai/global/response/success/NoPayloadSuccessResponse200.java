package com.ti.avaliai.global.response.success;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class NoPayloadSuccessResponse200<T> extends BaseSucessResponse<T> {


    @Schema(description = "Objeto genérico de resposta.")
    @Hidden
    @JsonIgnore
    public T getObject() {
        return this.object;
    }

    public NoPayloadSuccessResponse200(String path, String reason, LocalDateTime timestamp){
        super(200, path, reason, timestamp);
    }
}
