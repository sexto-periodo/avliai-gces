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
@Schema(description = "DTO para atualização de usuário")
public class UpdateUserRequestDTO {
    @Schema(description = "Nome do usuário", example = "Bruce")
    @JsonProperty("firstname")
    private String firstname;

    @Schema(description = "Sobrenome do usuário", example = "Wayne")
    @JsonProperty("lastname")
    private String lastname;

    @Schema(description = "Url da foto de perfil do usuário", example = "http://linkdafoto.com/imagem.png")
    @JsonProperty("profilePhotoUrl")
    private String profilePhotoUrl;




}