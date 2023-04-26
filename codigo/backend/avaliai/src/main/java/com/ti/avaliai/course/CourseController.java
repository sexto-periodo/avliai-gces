package com.ti.avaliai.course;

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
@RequestMapping(path = "/course")
public class CourseController extends BasicController {

    @Autowired
    private CourseService CourseService;

    @Operation(method = "GET", summary = "Buscar por Cursos", description = "Buscar por Cursos.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public ResponseEntity<BaseSucessResponse<List<CourseDTO>>> getCourse() {
        List<CourseDTO> coursesResponse = courseService.getCourses();
        return ok(coursesResponse);
    }


    @Operation(method = "POST", summary = "Cria um novo Curso", description = "Cria um novo Curso.")
    @ApiResponse(responseCode = "201", description = "CREATED")
    @PostMapping
    public ResponseEntity<NoPayloadSuccessResponse201> createCourse(@RequestBody @Valid CourseCreateRequestDTO courseCreateRequest) {
        courseService.create(courseCreateRequest);
        return created();
    }


    @Operation(method = "GET", summary = "Busca um Curso pelo id", description = "Busca um Curso pelo id.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(path = "{id}")
    public ResponseEntity<CourseDTO> findOneById(@PathVariable("id") long id) {
        CourseDTO response = courseService.findOneById(id);
        return ok(response);
    }

    @Operation(method = "GET", summary = "Deleta uma Disciplina pelo id", description = "Deleta uma Disciplina pelo id.")
    @ApiResponse(responseCode = "200", description = "OK")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<BaseSucessResponse> deleteCourse(@PathVariable("id") long id) {
        courseService.delete(id);
        return deleted();
    }

    @Operation(method = "PUT", summary = "Atualiza uma Disciplina", description = "Atualiza uma Disciplina.")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping
    public ResponseEntity<BaseSucessResponse<CourseDTO>> updateCourse( @RequestBody CourseDTO CourseUpdateRequest ) {

        CourseDTO response = courseService.update(courseUpdateRequest);
        return ok(response);
    }

}
