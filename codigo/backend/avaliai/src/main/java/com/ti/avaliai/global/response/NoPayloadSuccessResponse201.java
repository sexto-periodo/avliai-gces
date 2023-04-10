package com.ti.avaliai.global.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;

import java.time.LocalDateTime;

public class NoPayloadSuccessResponse201<T> extends BaseSucessResponse<T> {


    @Schema(description = "Objeto genérico de resposta.")
    @Hidden
    @JsonIgnore
    public T getObject() {
        return this.object;
    }

    public NoPayloadSuccessResponse201(String path, String reason, LocalDateTime timestamp){
        super(201, path, reason, timestamp);
    }
}
