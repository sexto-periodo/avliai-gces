package com.ti.avaliai.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ti.avaliai.user.Role;
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
@Schema(description = "DTO para criação de usuário")
public class UserCreateRequestDTO {

    @Schema(description = "Nome do usuário", example = "Bruce")
    @NotNull(message = "O campo 'name' não pode ser vazio.")
    @JsonProperty("name")
    private String firstname;

    @Schema(description = "Sobrenome do usuário", example = "Wayne")
    @NotNull(message = "O campo 'name' não pode ser vazio.")
    @JsonProperty("name")
    private String lastname;

    @Schema(description = "E-mail do usuário", example = "johndoe@example.com")
    @NotNull(message = "O campo 'email' não pode ser vazio.")
    @JsonProperty("email")
    private String email;

    @Schema(description = "Senha do usuário")
    @NotNull(message = "O campo 'password' não pode ser vazio.")
    @JsonProperty("password")
    private String password;

    @Schema(description = "HashId da universidade")
    @NotNull(message = "O Hash da universidade deve ser informado.")
    @JsonProperty("university")
    private String university;

    @Schema(description = "HashId dcurso")
    @NotNull(message = "O Hash do curso deve ser informado.")
    @JsonProperty("course")
    private String course;

    @Schema(description = "Role de usuário")
    @NotNull(message = "A Role deve ser informada.")
    @JsonProperty("role")
    private Role role;



}