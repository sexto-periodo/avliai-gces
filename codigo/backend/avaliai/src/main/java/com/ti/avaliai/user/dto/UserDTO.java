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
@Schema(description = "DTO para usuário")
public class UserDTO {

    @Schema(description = "ID do usuário")
    @JsonProperty("id")
    private long id;

    @Schema(description = "Hash ID do usuário")
    @JsonProperty("hashId")
    private String hashId;

    @Schema(description = "Nome do usuário")
    @NotNull(message = "O campo 'name' não pode ser vazio")
    @JsonProperty("name")
    private String name;

    @Schema(description = "E-mail do usuário")
    @NotNull(message = "O campo 'email' não pode ser vazio")
    @JsonProperty("email")
    private String email;

    @Schema(description = "Senha do usuário")
    @NotNull(message = "O campo 'password' não pode ser vazio")
    @JsonProperty("password")
    private String password;
}
