package com.ti.avaliai.academicmail;


import com.ti.avaliai.utils.TestUtils;
import org.apache.logging.log4j.util.Strings;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
        @Sql(scripts = "classpath:persistence/avaliai/university/before_test_university.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/course/before_test_course.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/academicemail/before_test_academic_email.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/subject/before_test_subject.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
})
public class AcademicEmailServiceTest {

    private static final String KNOWN_VALID_EMAIL = "user@sga.pucminas.br";
    private static final String KNOWN_INVALID_EMAIL = "user@gmail.com";
    private static final String EMPTY_EMAIL_STRING = Strings.EMPTY;

    @Autowired
    private AcademicEmailService academicEmailService;

    @Autowired
    private DataSource dataSource;

    @AfterEach
    public void clearDatabase() {
        TestUtils.clearDatabase(new JdbcTemplate(dataSource));
    }

    @DisplayName(value = "Teste de Sucesso - Verificar se um e-mail conhecido é válido e aceito pelo sistema")
    @Test
    void validEmail_Success(){
        assertEquals(true, academicEmailService.isValidEmail(KNOWN_VALID_EMAIL));
    }


    @DisplayName(value = "Teste de Falha - Verificar se um e-mail conhecido é inválido e recusado pelo sistema")
    @Test
    void invalidEmail_Failure(){
        assertEquals(false, academicEmailService.isValidEmail(KNOWN_INVALID_EMAIL));
    }

    @DisplayName(value = "Teste de Falha - Verificar uma string vazia como inválida e recusada pelo sistema")
    @Test
    void emptyEmailString_Failure(){
        assertFalse(academicEmailService.isValidEmail(EMPTY_EMAIL_STRING));
    }


}
