package com.ti.avaliai.auth;

import com.ti.avaliai.academicmail.AcademicMailService;
import com.ti.avaliai.auth.dto.RegisterRequestDTO;
import com.ti.avaliai.global.domain.exceptions.InvalidEmailException;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.ti.avaliai.user.Role.ADMIN;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationServiceTest {

    private static final int ALREADY_EXISTING_USERS = 0;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private AcademicMailService academicMailService;

    @BeforeEach
    void setup(){
        userService.deleteAll();
    }


    @DisplayName(value = "Teste de Sucesso - Cadastrar um novo usuário com sucesso")
    @Test
    public void createUser_Success(){
        var admin = RegisterRequestDTO.builder()
                .firstname("User")
                .lastname("User")
                .email("user@sga.pucminas.br")
                .password("1234")
                .universityHashId("543b45c583bfff6c30e44a751103a24f")
                .courseHashId("eb5ed7359d0bc0df70e6b7abf8584c5e")
                .role(ADMIN)
                .build();

        authenticationService.register(admin);

       List<User> users = userService.findAll();

        assertEquals(1,users.size());
        assertEquals("User", users.get(0).getFirstname());
        boolean validEmails = users.stream().allMatch((u) -> academicMailService.isValidEmail(u.getEmail()) );
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
                .universityHashId("543b45c583bfff6c30e44a751103a24f")
                .courseHashId("eb5ed7359d0bc0df70e6b7abf8584c5e")
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
                .universityHashId("543b45c583bfff6c30e44a751103a24f")
                .courseHashId("eb5ed7359d0bc0df70e6b7abf8584c5e")
                .role(ADMIN)
                .build();

        var user2 = RegisterRequestDTO.builder()
                .firstname("User")
                .lastname("User")
                .email("user1@sga.pucminas.br")
                .password("1234")
                .universityHashId("543b45c583bfff6c30e44a751103a24f")
                .courseHashId("eb5ed7359d0bc0df70e6b7abf8584c5e")
                .role(ADMIN)
                .build();

        authenticationService.register(user1);
        assertThrows(InvalidEmailException.class, () -> authenticationService.register(user2));

        List<User> users = userService.findAll();
        assertEquals(1,users.size());
    }

}
