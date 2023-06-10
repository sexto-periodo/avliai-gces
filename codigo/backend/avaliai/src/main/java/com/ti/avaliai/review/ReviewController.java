package com.ti.avaliai.review;

import com.ti.avaliai.global.domain.BasicController;
import com.ti.avaliai.global.response.success.BaseSucessResponse;
import com.ti.avaliai.global.response.success.NoPayloadSuccessResponse201;
import com.ti.avaliai.review.dto.CreateReviewRequestDTO;
import com.ti.avaliai.review.dto.ReviewDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path = "/review")
@Tag(name = "Review - Endpoints de Avalição de Disciplinas")
public class ReviewController extends BasicController {

    @Autowired
    private ReviewService reviewService;

    @Operation(method = "GET", summary = "Buscar por avaliaição através do HashId de Disciplina.", description = "Buscar por avaliaição através do HashId de Disciplina.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(value = "/subject/{subjectHashId}")
    public ResponseEntity<BaseSucessResponse<List<ReviewDTO>>> getReviewsBySubjectHashId(@PathVariable("subjectHashId") String subjectHashId) {
        List<ReviewDTO> reviews = reviewService.findAllBySubjectHashId(subjectHashId);
        return ok(reviews);
    }

    @Operation(method = "POST", summary = "Postar requisição de avaliação de Disciplina a fila.", description = "Postar requisição de avaliação de Disciplina a fila.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping
    public ResponseEntity<NoPayloadSuccessResponse201> getReviewsBySubjectHashId(@RequestBody @Valid CreateReviewRequestDTO request) {
        reviewService.send(request);
        return ok();
    }

    @Operation(method = "GET", summary = "Verifica se uma disciplina ja foi avaliada pelo usuário.", description = "verifica se uma disciplina ja foi avaliada pelo usuário.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(value = "/subject/already-reviewed-by-user/{subjectHashId}")
    public ResponseEntity<BaseSucessResponse<Boolean>> haveSubjectAlreadyReviewedByUser(@PathVariable("subjectHashId") String subjectHashId) {
        boolean response  = reviewService.haveUserAlreadyReviewedSubject(subjectHashId);
        return ok(response);
    }

    @Operation(method = "GET", summary = "Buscar por avaliaições feitas pelo usuário logado.", description = "Buscar por avaliaições feitas pelo usuário logado.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(value = "/user")
    public ResponseEntity<BaseSucessResponse<List<ReviewDTO>>> getReviewsByUser() {
        List<ReviewDTO> reviews = reviewService.findAllByLoggedUser();
        return ok(reviews);
    }
}
