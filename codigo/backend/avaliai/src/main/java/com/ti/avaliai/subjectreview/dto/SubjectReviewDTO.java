package com.ti.avaliai.subjectreview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Schema(description = "DTO para disciplina")
public class SubjectReviewDTO {

    @Schema(description = "ID da Avaliação.", example = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Hash ID da Avaliação.", example = "abc123")
    @JsonProperty("hashId")
    private String hashId;

    @Schema(description = "Contagem de votos.", example = "10")
    @JsonProperty("voteCount")
    private int voteCount;

    @Schema(description = "Texto da Avaliação.", example = "Lorem ipsum disciplina muito boa!")
    @JsonProperty("reviewText")
    private String reviewText;


}