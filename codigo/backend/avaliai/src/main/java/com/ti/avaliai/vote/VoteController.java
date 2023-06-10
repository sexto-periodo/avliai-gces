package com.ti.avaliai.vote;

import com.ti.avaliai.global.domain.BasicController;
import com.ti.avaliai.global.response.success.BaseSucessResponse;
import com.ti.avaliai.global.response.success.NoPayloadSuccessResponse201;
import com.ti.avaliai.vote.VoteService;
import com.ti.avaliai.vote.dto.VoteDTO;
import com.ti.avaliai.vote.dto.VoteRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(path = "/vote")
@Tag(name = "Vote - Endpoints de upvote e downvote de avaliações")
public class VoteController extends BasicController {

    @Autowired
    private VoteService voteService;

    @Operation(method = "PUT", summary = "Atualizar ou criar voto em uma avaliação.", description = "Atualizar ou criar voto em uma avaliação.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping
    public ResponseEntity<BaseSucessResponse<VoteDTO>> updateReviewVote(@RequestBody @Valid VoteRequestDTO request) {
        VoteDTO response = voteService.vote(request);
        return ok(response);
    }

}
