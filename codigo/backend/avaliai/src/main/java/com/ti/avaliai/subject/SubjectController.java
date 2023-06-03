package com.ti.avaliai.subject;

import com.ti.avaliai.global.domain.BasicController;
import com.ti.avaliai.global.response.success.BaseSucessResponse;
import com.ti.avaliai.global.response.success.NoPayloadSuccessResponse201;
import com.ti.avaliai.subject.dto.SubjectCreateRequestDTO;
import com.ti.avaliai.subject.dto.SubjectDTO;
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
@RequestMapping(path = "/subject")
@Tag(name = "Subject - Endpoints de Disciplinas")
public class SubjectController extends BasicController {

    @Autowired
    private SubjectService subjectService;

    @Operation(method = "GET", summary = "Buscar por Disciplinas", description = "Buscar por Disciplinas.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public ResponseEntity<BaseSucessResponse<List<SubjectDTO>>> getSubject() {
        List<SubjectDTO> subjectsResponse = subjectService.getSubjects();
        return ok(subjectsResponse);
    }


    @Operation(method = "POST", summary = "Cria uma nova Disciplina", description = "Cria uma nova Disciplina.")
    @ApiResponse(responseCode = "201", description = "CREATED")
    @PostMapping
    public ResponseEntity<NoPayloadSuccessResponse201> createSubject(@RequestBody @Valid SubjectCreateRequestDTO subjectCreateRequest) {
        subjectService.create(subjectCreateRequest);
        return created();
    }


    @Operation(method = "GET", summary = "Busca uma Disciplina pelo id", description = "Busca uma Disciplina pelo id.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(path = "/{id}")
    public ResponseEntity<SubjectDTO> findOneById(@PathVariable("id") long id) {
        SubjectDTO response = subjectService.findOneById(id);
        return ok(response);
    }

    @Operation(method = "DELETE", summary = "Deleta uma Disciplina pelo id", description = "Deleta uma Disciplina pelo id.")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<BaseSucessResponse> deleteSubject(@PathVariable("id") long id) {
        subjectService.delete(id);
        return deleted();
    }

    @Operation(method = "PUT", summary = "Atualiza uma Disciplina", description = "Atualiza uma Disciplina.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping
    public ResponseEntity<BaseSucessResponse<SubjectDTO>> updateSubject( @RequestBody SubjectDTO subjectUpdateRequest ) {

        SubjectDTO response = subjectService.update(subjectUpdateRequest);
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
