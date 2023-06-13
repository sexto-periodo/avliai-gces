package com.ti.avaliai.course;


import com.ti.avaliai.course.dto.CourseDTO;
import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.subject.Subject;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.subject.dto.SubjectDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    private static final String EXISTING_UNIVERSITY_HASH_ID = "543b45c583bfff6c30e44a751103a24f";
    private static final String NON_EXISTING_COURSE_HASH_ID = "9071e0eafc5d4a2f4afff4deb8e950fc";
    private static final String EXISTING_COURSE_HASH_ID = "1f061de68a7a0da8378fd30974dd1a98";

    @Test
    public void sameObject_Success() {
        CourseService courseService1 = new CourseService();
        assertTrue(courseService1.equals(courseService1));
    }

    @DisplayName(value = "Teste de Falha - Buscar curso por hashId inexistente")
    @Test
    public void findByHashId_Failure() {
        assertThrows(EntityNotFoundException.class, () -> courseService.findByHashId(NON_EXISTING_COURSE_HASH_ID));
    }

    @DisplayName(value = "Teste de Sucesso - Buscar todos os cursos relacionados a uma Universidade")
    @Test
    public void findCoursesByUniversity_Success() {
        List<CourseDTO> courses = courseService.findAllByUniversityHashId(EXISTING_UNIVERSITY_HASH_ID);

        assertTrue(courses.size() >= 1);
    }

    @DisplayName(value = "Teste de Sucesso - Buscar todos os cursos indicados pelaz lista de ids")
    @Test
    public void updateSubject_Failure() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);

        List<Course> courses = courseService.findAllByIdIn(ids);
        assertTrue(courses.size() == 2);
    }
}
