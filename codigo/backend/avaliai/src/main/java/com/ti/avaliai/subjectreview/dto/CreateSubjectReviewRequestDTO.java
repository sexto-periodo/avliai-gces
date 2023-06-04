package com.ti.avaliai.subjectreview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ti.avaliai.subjectreview.EReviewScore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubjectReviewRequestDTO {

    @Schema(description = "Texto da Avaliação.", example = "Lorem ipsum disciplina muito boa!")
    @JsonProperty("reviewText")
    private String reviewText;

    @Schema(description = "Nota da Avaliação.", example = "FIVE")
    @JsonProperty("score")
    private EReviewScore score;

    @NotNull
    @Schema(description = "HashId do Usuário.", example = "2657f123978992f799a0ba81736e46e3")
    @JsonProperty("userHashId")
    private  String userHashId;

    @NotNull
    @Schema(description = "HashId da Disciplina.", example = "17da1ae431f965d839ec8eb93087fb2b")
    @JsonProperty("subjectHashId")
    private  String subjectHashId;

    @NotNull
    @Schema(description = "HashId da universidade.", example = "543b45c583bfff6c30e44a751103a24f")
    @JsonProperty("universityHashId")
    private String universityHashId;

    @NotNull
    @Schema(description = "HashId do Curso.", example = "eb5ed7359d0bc0df70e6b7abf8584c5e")
    @JsonProperty("courseHashId")
    private String courseHashId;



}
