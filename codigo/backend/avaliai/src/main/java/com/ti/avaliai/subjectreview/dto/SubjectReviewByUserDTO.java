package com.ti.avaliai.subjectreview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Schema(description = "DTO para disciplina")
public class SubjectReviewByUserDTO extends  SubjectReviewDTO{
    @Schema(description = "Primeiro nome do usuário", example = "Bruce")
    @JsonProperty("firstname")
    private String firstname;

    @Schema(description = "Sobrenome do usuário", example = "Wayne")
    @JsonProperty("lastname")
    private String lastname;

}