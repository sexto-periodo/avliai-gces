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
    @JsonProperty("picUrl")
    private String picUrl;

    @Schema(description = "Campus da disciplina", example = "Campus A")
    @JsonProperty("campus")
    private String campus;

    @Schema(description = "HashId do curso", example = "12345678910112134h3j21kl44321f")
    @JsonProperty("courseHashId")
    private String courseHashId;

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