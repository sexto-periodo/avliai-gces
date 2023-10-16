package com.ti.avaliai.university;


import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.university.dto.UniversityCreateRequestDTO;
import com.ti.avaliai.university.dto.UniversityDTO;
import com.ti.avaliai.utils.JsonUtil;
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

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
        @Sql(scripts = "classpath:persistence/avaliai/university/before_test_university.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
})
public class UniversityServiceTest {

    private static final String EXISTING_UNIVERSITY_HASH_ID = "1d145f9110ce4e61af7f2363279816f5";
    private static final String NON_EXISTING_UNIVERSITY_HASH_ID = "178b21f9606d8d96c508eec3fe8c25bd";
    private static final String PATH_RESOURCE_JSON_UNIVERSITY = "/mock/university/university.json";

    @Autowired
    private UniversityService universityService;

    @Autowired
    private DataSource dataSource;

    @AfterEach
    public void clearDatabase() {
        TestUtils.clearDatabase(new JdbcTemplate(dataSource));
    }

    @Test
    public void create_NewUniversitySuccess() {

        UniversityCreateRequestDTO universityCreateRequestDTO = JsonUtil.objectFromJson("new_university", PATH_RESOURCE_JSON_UNIVERSITY, UniversityCreateRequestDTO.class);
        universityService.create(universityCreateRequestDTO);
    }

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
        assertTrue(universities.size() >= 2);
        assertTrue(universities.stream().anyMatch(u-> u.getHashId().equals(EXISTING_UNIVERSITY_HASH_ID)));
    }
}
