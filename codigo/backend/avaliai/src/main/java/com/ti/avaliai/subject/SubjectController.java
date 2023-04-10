package com.ti.avaliai.subject;

import java.util.List;


import com.ti.avaliai.global.domain.BasicController;
import com.ti.avaliai.global.response.BaseSucessResponse;
import com.ti.avaliai.global.response.NoPayloadSuccessResponse201;
import com.ti.avaliai.subject.dto.SubjectCreateRequestDTO;
import com.ti.avaliai.subject.dto.SubjectDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/subject")
public class SubjectController extends BasicController {

    @Autowired
    private SubjectService subjectService;

    @Operation(method = "GET", summary = "Buscar por disciplinas", description = "Buscar por disciplinas.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public ResponseEntity<BaseSucessResponse<List<SubjectDTO>>> getSubject() {
        List<SubjectDTO> subjectsResponse = subjectService.getSubjects();
        return ok(subjectsResponse);
    }


    @Operation(method = "POST", summary = "Cria uma nova disciplina", description = "Cria uma nova disciplina.")
    @ApiResponse(responseCode = "201", description = "CREATED")
    @PostMapping
    public ResponseEntity<NoPayloadSuccessResponse201> createSubject(@RequestBody @Valid SubjectCreateRequestDTO subjectCreateRequest) {
        subjectService.create(subjectCreateRequest);
        return created();
    }


    @Operation(method = "GET", summary = "Busca uma disciplina pelo id", description = "Busca uma disciplina pelo id.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(path = "{id}")
    public ResponseEntity<SubjectDTO> findOneById(@PathVariable("id") long id) {
        SubjectDTO response = subjectService.findOneById(id);
        return ok(response);
    }

    @Operation(method = "GET", summary = "Deleta uma disciplina pelo id", description = "Deleta uma disciplina pelo id.")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<BaseSucessResponse> deleteSubject(@PathVariable("id") long id) {
        subjectService.delete(id);
        return deleted();
    }

    @Operation(method = "PUT", summary = "Atualiza uma disciplina", description = "Atualiza uma disciplina.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping
    public ResponseEntity<BaseSucessResponse<SubjectDTO>> updateSubject( @RequestBody SubjectDTO subjectUpdateRequest ) {

        SubjectDTO response = subjectService.update(subjectUpdateRequest);
        return ok(response);
    }



}
