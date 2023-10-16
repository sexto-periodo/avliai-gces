package com.ti.avaliai.auth;

import com.ti.avaliai.academicmail.AcademicEmailService;
import com.ti.avaliai.auth.dto.RegisterRequestDTO;
import com.ti.avaliai.global.domain.exceptions.InvalidEmailException;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.utils.TestUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;

import javax.sql.DataSource;
import java.util.List;

import static com.ti.avaliai.user.Role.ADMIN;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
        @Sql(scripts = "classpath:persistence/avaliai/university/before_test_university.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/course/before_test_course.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/academicemail/before_test_academic_email.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
})
public class AuthenticationServiceTest {

    private static final int ALREADY_EXISTING_USERS = 0;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private AcademicEmailService academicEmailService;

    @Autowired
    private DataSource dataSource;

    @AfterEach
    public void clearDatabase() {
        TestUtils.clearDatabase(new JdbcTemplate(dataSource));
    }

    @DisplayName(value = "Teste de Sucesso - Cadastrar um novo usuário com sucesso")
    @Test
    public void createUser_Success(){
        var admin = RegisterRequestDTO.builder()
                .firstname("User")
                .lastname("User")
                .email("user@sga.pucminas.br")
                .password("1234")
                .universityHashId("1d145f9110ce4e61af7f2363279816f5")
                .courseHashId("534bf4699af840ffb99f95a1f7d44243")
                .role(ADMIN)
                .build();

        authenticationService.register(admin);

       List<User> users = userService.findAll();

        assertEquals(1,users.size());
        assertEquals("User", users.get(0).getFirstname());
        boolean validEmails = users.stream().allMatch((u) -> academicEmailService.isValidEmail(u.getEmail()) );
        assertTrue(validEmails);
    }

    @DisplayName(value = "Teste de Falha - Cadastrar usuário com e-mail inválido")
    @Test
    void createUserInvalidEmail_Failure(){
        var admin = RegisterRequestDTO.builder()
                .firstname("User")
                .lastname("User")
                .email("user@gmail.com")
                .password("1234")
                .universityHashId("1d145f9110ce4e61af7f2363279816f5")
                .courseHashId("534bf4699af840ffb99f95a1f7d44243")
                .role(ADMIN)
                .build();

        assertThrows(InvalidEmailException.class, () -> authenticationService.register(admin));

        List<User> users = userService.findAll();
        assertEquals(ALREADY_EXISTING_USERS,users.size());
    }

    @DisplayName(value = "Teste de Falha - Cadastrar usuário com e-mail já utilizado")
    @Test
    void createUserUsedEmail_Failure(){
        var user1 = RegisterRequestDTO.builder()
                .firstname("User")
                .lastname("User")
                .email("user1@sga.pucminas.br")
                .password("1234")
                .universityHashId("1d145f9110ce4e61af7f2363279816f5")
                .courseHashId("534bf4699af840ffb99f95a1f7d44243")
                .role(ADMIN)
                .build();

        var user2 = RegisterRequestDTO.builder()
                .firstname("User")
                .lastname("User")
                .email("user1@sga.pucminas.br")
                .password("1234")
                .universityHashId("1d145f9110ce4e61af7f2363279816f5")
                .courseHashId("534bf4699af840ffb99f95a1f7d44243")
                .role(ADMIN)
                .build();

        authenticationService.register(user1);
        assertThrows(InvalidEmailException.class, () -> authenticationService.register(user2));

        List<User> users = userService.findAll();
        assertEquals(1,users.size());
    }

}
