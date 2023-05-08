package com.ti.avaliai.university;

import com.ti.avaliai.global.domain.BasicController;
import com.ti.avaliai.global.response.BaseSucessResponse;
import com.ti.avaliai.global.response.NoPayloadSuccessResponse201;
import com.ti.avaliai.university.dto.UniversityCreateRequestDTO;
import com.ti.avaliai.university.dto.UniversityDTO;
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
@RequestMapping(path = "/university")
@Tag(name = "University - Endpoints universidades")
public class UniversityController extends BasicController {

    @Autowired
    private UniversityService universityService;

    @Operation(method = "GET", summary = "Buscar por Universidade", description = "Buscar por Universidades.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public ResponseEntity<BaseSucessResponse<List<UniversityDTO>>> getUniversity() {
        List<UniversityDTO> universitiesResponse = universityService.getUniversities();
        return ok(universitiesResponse);
    }


    @Operation(method = "POST", summary = "Cria uma nova Universidade", description = "Cria uma nova Universidade.")
    @ApiResponse(responseCode = "201", description = "CREATED")
    @PostMapping
    public ResponseEntity<NoPayloadSuccessResponse201> createUniversity(@RequestBody @Valid UniversityCreateRequestDTO universityCreateRequest) {
        universityService.create(universityCreateRequest);
        return created();
    }


    @Operation(method = "GET", summary = "Busca uma Universidade pelo id", description = "Busca uma Universidade pelo id.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(path = "{id}")
    public ResponseEntity<UniversityDTO> findOneById(@PathVariable("id") long id) {
        UniversityDTO response = universityService.findOneById(id);
        return ok(response);
    }

    @Operation(method = "GET", summary = "Deleta uma Universidadde pelo id", description = "Deleta uma Universidade pelo id.")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<BaseSucessResponse> deleteUniversity(@PathVariable("id") long id) {
        universityService.delete(id);
        return deleted();
    }

    @Operation(method = "PUT", summary = "Atualiza uma Universidade", description = "Atualiza uma Universidade.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping
    public ResponseEntity<BaseSucessResponse<UniversityDTO>> updateUniversity( @RequestBody UniversityDTO universityUpdateRequest ) {

        UniversityDTO response = universityService.update(universityUpdateRequest);
        return ok(response);
    }



}
