package com.ti.avaliai.academicmail;


import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AcademicMailServiceTest {

    private static final String KNOWN_VALID_EMAIL = "user@sga.pucminas.br";
    private static final String KNOWN_INVALID_EMAIL = "user@gmail.com";
    private static final String EMPTY_EMAIL_STRING = Strings.EMPTY;

    @Autowired
    private AcademicMailService academicMailService;

    @DisplayName(value = "Teste de Sucesso - Verificar se um e-mail conhecido é válido e aceito pelo sistema")
    @Test
    void validEmail_Success(){
        assertEquals(true, academicMailService.isValidEmail(KNOWN_VALID_EMAIL));
    }


    @DisplayName(value = "Teste de Falha - Verificar se um e-mail conhecido é inválido e recusado pelo sistema")
    @Test
    void invalidEmail_Failure(){
        assertEquals(false, academicMailService.isValidEmail(KNOWN_INVALID_EMAIL));
    }

    @DisplayName(value = "Teste de Falha - Verificar uma string vazia como inválida e recusada pelo sistema")
    @Test
    void emptyEmailString_Failure(){
        assertFalse(academicMailService.isValidEmail(EMPTY_EMAIL_STRING));
    }


}
