package com.ti.avaliai.course;

import com.ti.avaliai.course.dto.CourseCreateRequestDTO;
import com.ti.avaliai.course.dto.CourseDTO;
import com.ti.avaliai.course.dto.CourseUpdateRequestDTO;
import com.ti.avaliai.global.domain.BasicController;
import com.ti.avaliai.global.response.error.ErrorResponse404;
import com.ti.avaliai.global.response.success.BaseSucessResponse;
import com.ti.avaliai.global.response.success.NoPayloadSuccessResponse201;
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
@RequestMapping(path = "/course")
@Tag(name = "Course - Endpoints de cursos")

@ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse404.class))),
})
public class CourseController extends BasicController {

    @Autowired
    private CourseService courseService;

    @Operation(method = "GET", summary = "Busca por cursos relacionados a universidade.", description = "Busca por cursos relacionados a universidade.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(path = "/university/{universityHashId}")
    public ResponseEntity<BaseSucessResponse<List<CourseDTO>>> findByUniversity(@PathVariable("universityHashId") String univesityHashId) {
        List<CourseDTO> response = courseService.findAllByUniversityHashId(univesityHashId);
        return ok(response);
    }

}
