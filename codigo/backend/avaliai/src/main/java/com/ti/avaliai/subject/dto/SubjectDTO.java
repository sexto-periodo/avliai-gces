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
    @JsonProperty("hash_id")
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

    @Schema(description = "Nota da disciplina", example = "8.5")
    @JsonProperty("grade")
    private double grade;

}