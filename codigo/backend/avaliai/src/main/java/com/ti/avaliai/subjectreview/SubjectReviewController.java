package com.ti.avaliai.subjectreview;

import com.ti.avaliai.global.domain.BasicController;
import com.ti.avaliai.global.response.error.*;
import com.ti.avaliai.global.response.success.BaseSucessResponse;
import com.ti.avaliai.global.response.success.NoPayloadSuccessResponse201;
import com.ti.avaliai.subjectreview.dto.CreateSubjectReviewRequestDTO;
import com.ti.avaliai.subjectreview.dto.SubjectReviewDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path = "/subject-review")
@Tag(name = "SubjectReview - Endpoints de Avalição de Disciplinas")
public class SubjectReviewController extends BasicController {

    @Autowired
    private SubjectReviewService subjectReviewService;

    @Operation(method = "GET", summary = "Buscar por avaliaição através do HashId de Disciplina.", description = "Buscar por avaliaição através do HashId de Disciplina.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(value = "/subject/{subjectHashId}")
    public ResponseEntity<BaseSucessResponse<List<SubjectReviewDTO>>> getReviewsBySubjectHashId(@PathVariable("subjectHashId") String subjectHashId) {
        List<SubjectReviewDTO> reviews = subjectReviewService.findAllBySubjectHashId(subjectHashId);
        return ok(reviews);
    }

    @Operation(method = "POST", summary = "Postar requisição de avaliação de Disciplina a fila.", description = "Postar requisição de avaliação de Disciplina a fila.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping
    public ResponseEntity<NoPayloadSuccessResponse201> getReviewsBySubjectHashId(@RequestBody @Valid CreateSubjectReviewRequestDTO request) {
        subjectReviewService.send(request);
        return ok();
    }



}
