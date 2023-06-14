package com.ti.avaliai.rabbitreview;


import com.ti.avaliai.auth.AuthenticationService;
import com.ti.avaliai.rabbit.QueueStatusProcessorService;
import com.ti.avaliai.review.EReviewScore;
import com.ti.avaliai.review.Review;
import com.ti.avaliai.review.ReviewService;
import com.ti.avaliai.review.dto.CreateReviewRequestDTO;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.utils.UserTestUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RabbitReviewTest {

    private static final String EXISTING_SUBJECT_HASH_ID = "17da1ae431f965d839ec8eb93087fb2b";
    private static final String EXISTING_UNIVERSITY_HASH_ID = "543b45c583bfff6c30e44a751103a24f";
    private static final String EXISTING_COURSE_HASH_ID = "eb5ed7359d0bc0df70e6b7abf8584c5e";
    private static final String EXISTING_DEFAULT_USER_EMAIL = "testuser@sga.pucminas.br";

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserTestUtils userTestUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private QueueStatusProcessorService queueStatusProcessorService;

    private void sendGenericReviewMessage(User user) {
        CreateReviewRequestDTO message = CreateReviewRequestDTO.builder()
                .userHashId(user.getHashId())
                .subjectHashId(EXISTING_SUBJECT_HASH_ID)
                .universityHashId(EXISTING_UNIVERSITY_HASH_ID)
                .courseHashId(EXISTING_COURSE_HASH_ID)
                .reviewText("Lorem ipsum disciplina muito boa!")
                .score(EReviewScore.FIVE)
                .build();

        userTestUtils.setUserContextHolder(user);
        reviewService.send(message);
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
    void enqueueReviewRequest() {
        setup();
        sendGenericReviewMessage(userTestUtils.createDefaultTestUser());
        sendGenericReviewMessage(userTestUtils.createRandomTestUser());
    }

    @DisplayName(value = "Teste de Sucesso - Verificar o consumo e cadastro de avaliação")
    @Test
    @Order(2)
    void consumedReviewRequest_Success() {
        User user = userService.findByEmail(EXISTING_DEFAULT_USER_EMAIL);
        List<Review> reviews = reviewService.findAll();
        assertTrue( reviews.size() > 1);
        assertEquals(user.getEmail(), reviews.get(0).getUser().getEmail());
        assertEquals(user.getHashId(), reviews.get(0).getUser().getHashId());
        assertEquals(EReviewScore.FIVE, reviews.get(0).getScore());
    }

    @DisplayName(value = "Teste de Sucesso - Verificar fila vazia")
    @Test
    @Order(3)
    void queueEmpty_Success() {
        assertEquals(0, queueStatusProcessorService.getReviewQueueSize());
    }
}
