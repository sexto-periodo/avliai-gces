package com.ti.avaliai.usertest;

import com.ti.avaliai.global.domain.exceptions.AlreadyReviewedByUserException;
import com.ti.avaliai.subjectreview.EReviewScore;
import com.ti.avaliai.subjectreview.SubjectReview;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.dto.UpdateUserRequestDTO;
import com.ti.avaliai.user.dto.UserDTO;
import com.ti.avaliai.utils.UserTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    private static final String KNOWN_PROFILE_PHOTO_URL = "URL:profilePhoto";
    private static final String KNOWN_USER_FIRSTNAME = "Mestre";
    private static final String KNOWN_USER_LASTNAME = "Shifu";
    @Autowired
    private UserService userService;

    @Autowired
    private UserTestUtils userTestUtils;

    @DisplayName(value = "Teste de Sucesso - Utualizar os dados do usuário possiveis")
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

    @DisplayName(value = "Teste de Sucesso - Utualizar somente o campo Firstname")
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

    @DisplayName(value = "Teste de Sucesso - Utualizar somente o campo Lastname")
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

    @DisplayName(value = "Teste de Sucesso - Utualizar somente o campo profilePhotoUrl")
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
