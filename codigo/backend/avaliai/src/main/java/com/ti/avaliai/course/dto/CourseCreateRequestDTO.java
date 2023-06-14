package com.ti.avaliai.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ti.avaliai.subject.Subject;
import com.ti.avaliai.university.University;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseCreateRequestDTO {

    @Schema(description = "Nome do Curso.", example = "Engenharia de Diagramas")
    @NotBlank(message = "O nome do Curso deve ser informado.")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Duração do Curso.", example = "10")
    @NotNull(message = "A duração do Curso deve ser informada.")
    @JsonProperty("overtime")
    private int overtime;

    @Schema(description = "Status do curso.", example = "true")
    @NotNull(message = "O Status do curso deve ser informado.")
    @JsonProperty("statusCurriculum")
    private boolean statusCurriculum;

    @Schema(description = "Id da universidade.", example = "10")
    @NotNull(message = "A universidade deve ser informada.")
    @JsonProperty("universityId")
    private Long universityId;

}