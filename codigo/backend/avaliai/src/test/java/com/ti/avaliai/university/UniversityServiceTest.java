package com.ti.avaliai.university;


import com.ti.avaliai.course.Course;
import com.ti.avaliai.course.CourseService;
import com.ti.avaliai.course.dto.CourseDTO;
import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.university.dto.UniversityDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UniversityServiceTest {

    private static final String EXISTING_UNIVERSITY_HASH_ID = "543b45c583bfff6c30e44a751103a24f";
    private static final String NON_EXISTING_UNIVERSITY_HASH_ID = "178b21f9606d8d96c508eec3fe8c25bd";

    @Autowired
    private UniversityService universityService;

    @Test
    public void sameObject_Success() {
        UniversityService universityService1 = new UniversityService();
        assertTrue(universityService1.equals(universityService1));
    }

    @DisplayName(value = "Teste de Falha - Buscar universidade por hashId inexistente")
    @Test
    public void findByHashId_Failure() {
        assertThrows(EntityNotFoundException.class,
                () -> universityService.findByHashId(NON_EXISTING_UNIVERSITY_HASH_ID));
    }

    @DisplayName(value = "Teste de Sucesso - Buscar universidade por hashId")
    @Test
    public void findByHashId_Success() {
        University university = universityService.findByHashId(EXISTING_UNIVERSITY_HASH_ID);
        assertEquals(EXISTING_UNIVERSITY_HASH_ID, university.getHashId());
    }

    @DisplayName(value = "Teste de Falha - [DTO] - Buscar universidade por hashId inexistente")
    @Test
    public void findByHashIdDTO_Failure() {
        assertThrows(EntityNotFoundException.class,
                () -> universityService.findByHashIdDTO(NON_EXISTING_UNIVERSITY_HASH_ID));
    }

    @DisplayName(value = "Teste de Sucesso - [DTO] - Buscar universidade por hashId")
    @Test
    public void findByHashIdDTO_Success() {
        UniversityDTO universityDTO = universityService.findByHashIdDTO(EXISTING_UNIVERSITY_HASH_ID);
        assertEquals(EXISTING_UNIVERSITY_HASH_ID, universityDTO.getHashId());
        assertTrue(universityDTO instanceof UniversityDTO);
    }

    @DisplayName(value = "Teste de Sucesso - Buscar por todas universidades")
    @Test
    public void findAll_Success() {
        List<UniversityDTO> universities = universityService.findAll();
        assertTrue(universities.size() == 2);
        assertTrue(universities.stream().anyMatch(u-> u.getHashId().equals(EXISTING_UNIVERSITY_HASH_ID)));
    }



}
