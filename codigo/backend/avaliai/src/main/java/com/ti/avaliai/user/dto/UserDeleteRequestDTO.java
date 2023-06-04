package com.ti.avaliai.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDeleteRequestDTO {
    @Schema(description = "E-mail do usuário a ser excluído", example = "johndoe@example.com")
    @NotNull(message = "O campo 'email' não pode ser vazio")
    @JsonProperty("email")
    private String email;
}
