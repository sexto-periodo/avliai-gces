package com.ti.avaliai.university;

import com.ti.avaliai.global.domain.BasicController;
import com.ti.avaliai.global.response.success.BaseSucessResponse;
import com.ti.avaliai.university.dto.UniversityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<BaseSucessResponse<List<UniversityDTO>>> findAll() {
        List<UniversityDTO> response = universityService.findAll();
        return ok(response);
    }

    @Operation(method = "GET", summary = "Busca uma Universidade pelo HashId", description = "Busca uma Universidade pelo id.")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(path = "{hashId}")
    public ResponseEntity<UniversityDTO> findOneByHashId(@PathVariable("hashId") String hashId) {
        UniversityDTO response = universityService.findByHashIdDTO(hashId);
        return ok(response);
    }
}
