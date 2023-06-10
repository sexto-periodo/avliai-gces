package com.ti.avaliai.vote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Schema(description = "DTO para Votos")
public class VoteDTO {

    @Schema(description = "Voto adicionado ou removido", example = "true")
    @JsonProperty("isVoted")
    private boolean isVoted;

    @Schema(description = "Voto a favor ou contra", example = "true")
    @JsonProperty("voteUpDown")
    private boolean voteUpDown;

    @Schema(description = "HashId da Avaliação", example = "9198136f262aa656367a6227ec5fbdad")
    @JsonProperty("subjectReviewHashId")
    private String reviewHashId;

    @Schema(description = "Contagem de votos.", example = "10")
    @JsonProperty("voteCount")
    private int voteCount;
}
