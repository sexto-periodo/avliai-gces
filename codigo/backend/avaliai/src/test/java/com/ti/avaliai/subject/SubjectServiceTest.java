package com.ti.avaliai.subject;

import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.subject.dto.SubjectDTO;
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


@SqlGroup({
        @Sql(scripts = "classpath:persistence/avaliai/university/before_test_university.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/course/before_test_course.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/academicemail/before_test_academic_email.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/subject/before_test_subject.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubjectServiceTest {

    @Autowired
    private SubjectService subjectService;

    private static final String NON_EXISTING_SUBJECT_HASH_ID = "76ce8550a7e5d90946f83f59e6b459cf";
    private static final String EXISTING_COURSE_HASH_ID = "534bf4699af840ffb99f95a1f7d44243";
    private static final int ALREADY_EXISTING_SUBJECTS = 47;

    @Autowired
    private DataSource dataSource;

    @AfterEach
    public void clearDatabase() {
        TestUtils.clearDatabase(new JdbcTemplate(dataSource));
    }

    @Test
    public void sameObject_Success() {
        SubjectService subjectService = new SubjectService();
        assertTrue(subjectService.equals(subjectService));
    }

    @DisplayName(value = "Teste de Falha - Buscar disciplina relacionadas por hashId inexistente")
    @Test
    public void findByHashId_Success() {
        assertThrows(EntityNotFoundException.class, () -> subjectService.findByHashId(NON_EXISTING_SUBJECT_HASH_ID));
    }
    @DisplayName(value = "Teste de Sucesso - Buscar todas as disciplinas relacionadas a um curso")
    @Test
    public void finSubjectByCourse_Success() {
        List<SubjectDTO> subjects = subjectService.findAllByCourseHashId(EXISTING_COURSE_HASH_ID);

        assertTrue(subjects.size() > 5);
    }

    @DisplayName(value = "Teste de Sucesso - Buscar todas as disciplinas do sistema")
    @Test
    public void findAllSubjects_Success() {
        List<SubjectDTO> subjectDTOs = subjectService.findAllDTO();

        assertTrue(subjectDTOs.size() >= ALREADY_EXISTING_SUBJECTS);
    }

}
