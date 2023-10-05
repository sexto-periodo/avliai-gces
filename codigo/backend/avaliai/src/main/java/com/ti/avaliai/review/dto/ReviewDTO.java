package com.ti.avaliai.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ti.avaliai.vote.dto.VoteDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Schema(description = "DTO para Avaliação de Disciplina")
public class ReviewDTO {

    @Schema(description = "ID da Avaliação.", example = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Hash ID da Avaliação.", example = "abc123")
    @JsonProperty("hashId")
    private String hashId;


    @Schema(description = "Texto da Avaliação.", example = "Lorem ipsum disciplina muito boa!")
    @JsonProperty("reviewText")
    private String reviewText;

    @Schema(description = "Status dos votos da avaliação.")
    @JsonProperty("vote")
    private VoteDTO vote;
}