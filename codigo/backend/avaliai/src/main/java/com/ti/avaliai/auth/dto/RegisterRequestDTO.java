package com.ti.avaliai.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ti.avaliai.user.Role;
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
public class RegisterRequestDTO {

  @NotNull
  @Schema(description = "Primeiro nome do usuário", example = "Clark")
  @JsonProperty(namespace = "firstname")
  private String firstname;

  @NotNull
  @JsonProperty(namespace = "lastname")
  @Schema(description = "Último nome do usuário", example = "Kent")
  private String lastname;

  @NotNull
  @JsonProperty(namespace = "email")
  @Schema(description = "E-mail do usuário", example = "superman@sga.pucminas.br")
  private String email;

  @NotNull
  @JsonProperty(namespace = "password")
  @Schema(description = "Senha do usuário", example = "senha1234")
  private String password;

  @NotNull
  @JsonProperty(namespace = "role")
  @Schema(description = "Função/Permissão do usuário", example = "USER")
  private Role role;

  @NotNull
  @Schema(description = "HashId da universidade", example = "543b45c583bfff6c30e44a751103a24f")
  @JsonProperty("universityHashId")
  private String universityHashId;

  @NotNull
  @Schema(description = "HashId do curso", example = "eb5ed7359d0bc0df70e6b7abf8584c5e")
  @JsonProperty("courseHashId")
  private String courseHashId;
}
