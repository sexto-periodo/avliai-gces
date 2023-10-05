package com.ti.avaliai.subject;

import com.ti.avaliai.global.domain.BasicController;
import com.ti.avaliai.global.response.success.BaseSucessResponse;
import com.ti.avaliai.subject.dto.SubjectDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/subject")
@Tag(name = "Subject - Endpoints de Disciplinas")
public class SubjectController extends BasicController {

    @Operation(method = "GET", summary = "Busca todas as disciplinas.", description = "Busca todas as disciplinas.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public ResponseEntity<BaseSucessResponse<List<SubjectDTO>>> faindAll() {
        List<SubjectDTO> response = subjectService.findAllDTO();
        return ok(response);
    }

    @Autowired
    private SubjectService subjectService;

    @Operation(method = "GET", summary = "Busca uma Disciplina pelo HashId", description = "Busca uma Disciplina pelo HashId.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(path = "/{hashId}")
    public ResponseEntity<SubjectDTO> findByHashId(@PathVariable("hashId") String hashId) {
        SubjectDTO response = subjectService.findByHashIdDTO(hashId);
        return ok(response);
    }

    @Operation(method = "GET", summary = "Busca por disciplinas relacionados ao curso.", description = "Busca por disciplinas relacionados ao curso.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(path = "/course/{courseHashId}")
    public ResponseEntity<BaseSucessResponse<List<SubjectDTO>>> findByUniversity(@PathVariable("courseHashId") String courseHashId) {
        List<SubjectDTO> response = subjectService.findAllByCourseHashId(courseHashId);
        return ok(response);
    }
}
