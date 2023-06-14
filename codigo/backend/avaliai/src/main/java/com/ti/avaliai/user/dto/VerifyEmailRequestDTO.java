package com.ti.avaliai.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "DTO para requisição de validação de e-mail")
public class VerifyEmailRequestDTO {

    @Schema(description = "ID do usuário", example = "admin@sga.pucminas.br")
    @NotBlank(message = "O nome do e-mail deve ser informado.")
    @JsonProperty("email")
    private String email;
}
