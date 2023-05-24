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
@Schema(description = "DTO para curso")
public class CourseDTO {

    @Schema(required = true, description = "Id do Curso.", example = "123456")
    @NotBlank(message = "O id do Curso deve ser informado.")
    @JsonProperty("id")
    private Long id;

    @Schema(required = true, description = "HashId do Curso.", example = "123456h2314huipcsanmfhudsa321")
    @NotBlank(message = "O hashId do Curso deve ser informado.")
    @JsonProperty("hashId")
    private  String hashId;

    @Schema(required = true, description = "Nome do Curso.", example = "Engenharia de Diagramas")
    @NotBlank(message = "O nome do Curso deve ser informado.")
    @JsonProperty("name")
    private String name;

    @Schema(required = true, description = "Duração do Curso.", example = "10")
    @NotBlank(message = "A duração do Curso deve ser informada.")
    @JsonProperty("overtime")
    private int overtime;
    private List<Subject> subjects;

    @Schema(required = true, description = "Status do Curso.", example = "true")
    @JsonProperty("statusCurriculum")
    private boolean statusCurriculum;

}