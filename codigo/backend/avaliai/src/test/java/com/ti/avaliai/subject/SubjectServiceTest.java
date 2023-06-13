package com.ti.avaliai.subject;

import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.subject.dto.SubjectCreateRequestDTO;
import com.ti.avaliai.subject.dto.SubjectDTO;
import com.ti.avaliai.subject.dto.SubjectUpdateRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubjectServiceTest {

    @Autowired
    private SubjectService subjectService;

    private static final String EXISTING_SUBJECT_HASH_ID = "17da1ae431f965d839ec8eb93087fb2b";
    private static final String NON_EXISTING_SUBJECT_HASH_ID = "76ce8550a7e5d90946f83f59e6b459cf";
    private static final String EXISTING_COURSE_HASH_ID = "1f061de68a7a0da8378fd30974dd1a98";
    private static final int ALREADY_EXISTING_SUBJECTS = 47;

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

    @DisplayName(value = "Teste de Sucesso - Buscar todas as disciplinas indicadas pela lista de ids")
    @Test
    public void updateSubject_Success() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        ids.add(4L);
        ids.add(5L);
        ids.add(6L);

        List<Subject> subjects = subjectService.findAllByIdIn(ids);
        assertTrue(subjects.size() == 6);
    }

    @DisplayName(value = "Teste de Sucesso - Buscar todas as disciplinas do sistema")
    @Test
    public void findAllSubjects_Success() {
        List<SubjectDTO> subjectDTOs = subjectService.findAll();

        assertTrue(subjectDTOs.size() >= ALREADY_EXISTING_SUBJECTS);
    }

}
