package com.ti.avaliai.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ti.avaliai.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Primeiro nome do usuário", example = "Bruce")
    @JsonProperty("firstname")
    private String firstname;

    @Schema(description = "Sobrenome do usuário", example = "Wayne")
    @JsonProperty("lastname")
    private String lastname;

    @Schema(description = "E-mail do usuário")
    @JsonProperty("email")
    private String email;

    @Schema(description = "HashId do Curso do usuário.", example = "1234124314n32j1l4h321432143214gfdsa")
    @JsonProperty("courseHashId")
    private String courseHashId;

    @Schema(description = "HashId da universidade do usuário.", example = "080712093214nhc21hh12c3hcx12hlsd")
    @JsonProperty("universityHashId")
    private String universityHashId;

    @Schema(description = "Role de permissão do usuário")
    @JsonProperty("role")
    private Role role;
}
