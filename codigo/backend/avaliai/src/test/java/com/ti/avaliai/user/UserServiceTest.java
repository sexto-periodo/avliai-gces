package com.ti.avaliai.user;

import com.ti.avaliai.user.dto.UpdateUserRequestDTO;
import com.ti.avaliai.user.dto.UserDTO;
import com.ti.avaliai.utils.TestUtils;
import com.ti.avaliai.utils.UserTestUtils;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
        @Sql(scripts = "classpath:persistence/avaliai/university/before_test_university.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/course/before_test_course.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/academicemail/before_test_academic_email.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
})
public class UserServiceTest {

    private static final String KNOWN_PROFILE_PHOTO_URL = "URL:profilePhoto";
    private static final String KNOWN_USER_FIRSTNAME = "Mestre";
    private static final String KNOWN_USER_LASTNAME = "Shifu";
    @Autowired
    private UserService userService;

    @Autowired
    private UserTestUtils userTestUtils;

    @Autowired
    private DataSource dataSource;

    @AfterEach
    public void clearDatabase() {
        TestUtils.clearDatabase(new JdbcTemplate(dataSource));
    }

    @DisplayName(value = "Teste de Sucesso - Atualizar os dados do usuário possiveis")
    @Test
    void updateUserInfo_Success() {
        User user = userTestUtils.createDefaultTestUser();

        UpdateUserRequestDTO updateRequest = UpdateUserRequestDTO.builder()
                .profilePhotoUrl(KNOWN_PROFILE_PHOTO_URL)
                .firstname(KNOWN_USER_FIRSTNAME)
                .lastname(KNOWN_USER_LASTNAME)
                .build();

        userTestUtils.setUserContextHolder(user);

        UserDTO userDTO = userService.updateUser(updateRequest);

        assertEquals(KNOWN_PROFILE_PHOTO_URL, userDTO.getProfilePhotoUrl() );
        assertEquals(KNOWN_USER_FIRSTNAME, userDTO.getFirstname());
        assertEquals(KNOWN_USER_LASTNAME, userDTO.getLastname());
    }

    @DisplayName(value = "Teste de Sucesso - Atualizar somente o campo Firstname")
    @Test
    void updateOnlyFirstnameUserInfo_Success() {
        User user = userTestUtils.createDefaultTestUser();

        UpdateUserRequestDTO updateRequest = UpdateUserRequestDTO.builder()
                .firstname(KNOWN_USER_FIRSTNAME)
                .build();

        userTestUtils.setUserContextHolder(user);

        UserDTO userDTO = userService.updateUser(updateRequest);

        assertEquals(KNOWN_USER_FIRSTNAME, userDTO.getFirstname());
        assertEquals(user.getProfilePhotoUrl(), userDTO.getProfilePhotoUrl() );
        assertEquals(user.getLastname(), userDTO.getLastname());
    }

    @DisplayName(value = "Teste de Sucesso - Atualizar somente o campo Lastname")
    @Test
    void updateOnlyLastnameUserInfo_Success() {
        User user = userTestUtils.createDefaultTestUser();

        UpdateUserRequestDTO updateRequest = UpdateUserRequestDTO.builder()
                .lastname(KNOWN_USER_LASTNAME)
                .build();

        userTestUtils.setUserContextHolder(user);

        UserDTO userDTO = userService.updateUser(updateRequest);

        assertEquals(KNOWN_USER_LASTNAME, userDTO.getLastname());
        assertEquals(user.getProfilePhotoUrl(), userDTO.getProfilePhotoUrl() );
        assertEquals(user.getFirstname(), userDTO.getFirstname());
    }

    @DisplayName(value = "Teste de Sucesso - Atualizar somente o campo profilePhotoUrl")
    @Test
    void updateOnlyProfilePhotoUrlUserInfo_Success() {
        User user = userTestUtils.createDefaultTestUser();

        UpdateUserRequestDTO updateRequest = UpdateUserRequestDTO.builder()
                .profilePhotoUrl(KNOWN_PROFILE_PHOTO_URL)
                .build();

        userTestUtils.setUserContextHolder(user);

        UserDTO userDTO = userService.updateUser(updateRequest);

        assertEquals(KNOWN_PROFILE_PHOTO_URL, userDTO.getProfilePhotoUrl() );
        assertEquals(user.getFirstname(), userDTO.getFirstname());
        assertEquals(user.getLastname(), userDTO.getLastname());
    }
}
