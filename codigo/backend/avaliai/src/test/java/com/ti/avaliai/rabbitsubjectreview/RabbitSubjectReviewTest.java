package com.ti.avaliai.rabbitsubjectreview;


import com.ti.avaliai.auth.AuthenticationService;
import com.ti.avaliai.auth.dto.AuthenticationRequestDTO;
import com.ti.avaliai.rabbit.QueueStatusProcessorService;
import com.ti.avaliai.subjectreview.EReviewScore;
import com.ti.avaliai.subjectreview.SubjectReview;
import com.ti.avaliai.subjectreview.SubjectReviewService;
import com.ti.avaliai.subjectreview.dto.CreateSubjectReviewRequestDTO;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserContextHolder;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.utils.UserTestUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RabbitSubjectReviewTest {

    private static final String EXISTING_SUBJECT_HASH_ID = "17da1ae431f965d839ec8eb93087fb2b";
    private static final String EXISTING_UNIVERSITY_HASH_ID = "543b45c583bfff6c30e44a751103a24f";
    private static final String EXISTING_COURSE_HASH_ID = "eb5ed7359d0bc0df70e6b7abf8584c5e";
    private static final String EXISTING_DEFAULT_USER_EMAIL = "testuser@sga.pucminas.br";

    @Autowired
    private SubjectReviewService subjectReviewService;

    @Autowired
    private UserTestUtils userTestUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private QueueStatusProcessorService queueStatusProcessorService;

    private void sendGenericReviewMessage(User user) {
        CreateSubjectReviewRequestDTO message = CreateSubjectReviewRequestDTO.builder()
                .userHashId(user.getHashId())
                .subjectHashId(EXISTING_SUBJECT_HASH_ID)
                .universityHashId(EXISTING_UNIVERSITY_HASH_ID)
                .courseHashId(EXISTING_COURSE_HASH_ID)
                .reviewText("Lorem ipsum disciplina muito boa!")
                .score(EReviewScore.FIVE)
                .build();

        userTestUtils.setUserContextHolder(user);
        subjectReviewService.send(message);
        authenticationService.logout(user);
        authenticationService.clearAllTokens();
        userTestUtils.clearUserContextHolder();
    }

    void setup(){
        userService.deleteAll();
        authenticationService.clearAllTokens();
        userTestUtils.clearUserContextHolder();
    }
    @BeforeAll
    void enqueueSubjectReviewRequest() {
        setup();
        sendGenericReviewMessage(userTestUtils.createDefaultTestUser());
        sendGenericReviewMessage(userTestUtils.createRandomTestUser());
    }

    @DisplayName(value = "Teste de Sucesso - Verificar o consumo e cadastro de avaliação")
    @Test
    @Order(2)
    void consumedReviewRequest_Success() {
        User user = userService.findByEmail(EXISTING_DEFAULT_USER_EMAIL);
        List<SubjectReview> reviews = subjectReviewService.findAll();
        assertTrue( reviews.size() > 1);
        assertEquals(user.getEmail(), reviews.get(0).getUser().getEmail());
        assertEquals(user.getHashId(), reviews.get(0).getUser().getHashId());
        assertEquals(EReviewScore.FIVE, reviews.get(0).getScore());
    }

    @DisplayName(value = "Teste de Sucesso - Verificar fila vazia")
    @Test
    @Order(3)
    void queueEmpty_Success() {
        assertEquals(0, queueStatusProcessorService.getSubjectReviewQueueSize());
    }
}
