package com.ti.avaliai.university.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ti.avaliai.course.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UniversityUpdateRequestDTO {

    @Schema(description = "ID da universidade", example = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Hash ID da universidade", example = "abc123")
    @JsonProperty("hashId")
    private String hashId;

    @Schema(description = "Nome da universidade", example = "Universidade X")
    @JsonProperty("name")
    private String name;

    @Schema(description = "CNPJ da universidade", example = "12.345.678/0001-90")
    @JsonProperty("cnpj")
    private String cnpj;

    @Schema(description = "Lista de cursos oferecidos pela universidade")
    @JsonProperty("courses")
    private List<Long> coursesIds;

}
