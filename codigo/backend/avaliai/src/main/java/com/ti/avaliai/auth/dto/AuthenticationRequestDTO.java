package com.ti.avaliai.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {

  @Schema(description = "E-mail do Usuário.", example = "admin@sga.pucminas.br")
  @NotNull(message = "O E-mail do usuario deve ser informado")
  @JsonProperty("email")
  private String email;

  @Schema(description = "Senha do Usuário.", example = "1234")
  @NotNull(message = "A senha do usuario deve ser informado")
  @JsonProperty("password")
  private String password;
}
