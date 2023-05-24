package com.ti.avaliai.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ti.avaliai.subject.Subject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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

    @Schema(required = true, description = "Nome do Curso.", example = "Engenharia de Diagramas")
    @NotBlank(message = "O nome do Curso deve ser informado.")
    @JsonProperty("name")
    private String name;

    @Schema(required = true, description = "Duração do Curso.", example = "10")
    @NotBlank(message = "A duração do Curso deve ser informada.")
    @JsonProperty("overtime")
    private int overtime;

    private List<Subject> subjects;
    private boolean statusCurriculum;

}