package com.ti.avaliai.subject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "DTO para disciplina")
public class SubjectDTO {

    @Schema(description = "ID da disciplina", example = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Hash ID da disciplina", example = "abc123")
    @JsonProperty("hashId")
    private String hashId;

    @Schema(description = "Nome da disciplina", example = "Matemática")
    @JsonProperty("name")
    private String name;

    @Schema(description = "URL da imagem da disciplina", example = "https://example.com/image.jpg")
    @JsonProperty("imageUrl")
    private String imageUrl;

    @Schema(description = "Campus da disciplina", example = "Campus A")
    @JsonProperty("campus")
    private String campus;

    @Schema(description = "Nome do Curso", example = "Engenharia de Software")
    @JsonProperty("course")
    private String course;

    @Schema(description = "Nome da universidade.", example = "PUC Minas")
    @JsonProperty("university")
    private String university;

    @Schema(description = "HashId do curso", example = "1f061de68a7a0da8378fd30974dd1a98")
    @JsonProperty("courseHashId")
    private String courseHashId;

    @Schema(description = "HashId da universidade", example = "543b45c583bfff6c30e44a751103a24f")
    @JsonProperty("universityHashId")
    private String universityHashId;

    @Schema(description = "Descrição curta da disciplina.", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent pretium lorem vitae libero vulputate, sed gravida arcu eleifend. Phasellus euismod tristique malesuada. Vestibulum quis lacus vitae ligula consectetur fringilla")
    @JsonProperty("shortDescription")
    private String shortDescription;

    @Schema(description = "Descrição longa da disciplina.", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent pretium lorem vitae libero vulputate, sed gravida arcu eleifend. Phasellus euismod tristique malesuada. Vestibulum quis lacus vitae ligula consectetur fringilla")
    @JsonProperty("longDescription")
    private String longDescription;

    @Schema(description = "Avaliação média da disciplina.", example = "4.9")
    @JsonProperty("score")
    private double score;



}