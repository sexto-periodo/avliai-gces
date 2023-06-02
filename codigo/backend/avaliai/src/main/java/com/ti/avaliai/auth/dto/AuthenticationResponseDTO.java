package com.ti.avaliai.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDTO {

  @Schema(description = "Token de acesso do usuário.", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBzZ2EucHVjbWluYXMuYnIiLCJpYXQiOjE2ODU0MTc4MTYsImV4cCI6MTY4NTUwNDIxNn0.4d2eH_mL7A24sdyvqH3afSY-fQeaX8IFE8u0mos7HK0")
  @JsonProperty("access_token")
  private String accessToken;

  @Schema(description = "Token para geração de um novo token", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1haWwuY29tIiwiaWF0IjoxNjg1NDE3ODE2LCJleHAiOjE2ODU1MDQyMTZ9.Akcgqk0jR-R4bPBB0Tc8GhqbO7C2df79aUx-bPRz8qQ")
  @JsonProperty("refresh_token")
  private String refreshToken;
}
