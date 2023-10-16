package com.ti.avaliai.course;


import com.ti.avaliai.course.dto.CourseDTO;
import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
        @Sql(scripts = "classpath:persistence/avaliai/university/before_test_university.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/course/before_test_course.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
})
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private DataSource dataSource;

    private static final String EXISTING_UNIVERSITY_HASH_ID = "1d145f9110ce4e61af7f2363279816f5";
    private static final String NON_EXISTING_COURSE_HASH_ID = "9071e0eafc5d4a2f4afff4deb8e950fc";
    private static final String EXISTING_COURSE_HASH_ID = "534bf4699af840ffb99f95a1f7d44243";


    @AfterEach
    public void clearDatabase() {
        TestUtils.clearDatabase(new JdbcTemplate(dataSource));
    }
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
}
