package com.ti.avaliai.reviewtest;


import com.ti.avaliai.auth.AuthenticationService;
import com.ti.avaliai.global.domain.exceptions.AlreadyReviewedByUserException;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.review.EReviewScore;
import com.ti.avaliai.review.Review;
import com.ti.avaliai.review.ReviewService;
import com.ti.avaliai.review.dto.CreateReviewRequestDTO;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.utils.UserTestUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ti.avaliai.user.User;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SuibjectReviewServiceTest {

    // Calcular upvotes e downvotes corretamente

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
    private SubjectService subjectService;

    @Autowired
    private AuthenticationService authenticationService;


    private void sendGenericReviewMessage(User user, EReviewScore score) {
        CreateReviewRequestDTO message = CreateReviewRequestDTO.builder()
                .userHashId(user.getHashId())
                .subjectHashId(EXISTING_SUBJECT_HASH_ID)
                .universityHashId(EXISTING_UNIVERSITY_HASH_ID)
                .courseHashId(EXISTING_COURSE_HASH_ID)
                .reviewText("Lorem ipsum disciplina muito boa!")
                .score(score)
                .build();

        userTestUtils.setUserContextHolder(user);
        reviewService.send(message);
        authenticationService.logout(user);
        authenticationService.clearAllTokens();
        userTestUtils.clearUserContextHolder();
    }

    @DisplayName(value = "Teste de Sucesso - Media de avaliação calulada corretamente")
    @Test
    void reviewSubjectAverageScore_Success() {
        sendGenericReviewMessage(userTestUtils.createDefaultTestUser(), EReviewScore.FIVE);
        sendGenericReviewMessage(userTestUtils.createRandomTestUser(), EReviewScore.ONE);


        assertTrue(reviewService.findAll().size() >= 1);
        assertEquals(3, subjectService.findByHashIdDTO(EXISTING_SUBJECT_HASH_ID).getScore());
    }

    @DisplayName(value = "Teste de Falha - Não permitir a avaliação da mesma disciplina pelo mesmo usuário")
    @Test
    void alreadyReviewedByUser_Failure() {
        User user = userTestUtils.createDefaultTestUser();
        sendGenericReviewMessage(user, EReviewScore.FIVE);
        sendGenericReviewMessage(userTestUtils.createRandomTestUser(), EReviewScore.ONE);

        CreateReviewRequestDTO message = CreateReviewRequestDTO.builder()
                .userHashId(user.getHashId())
                .subjectHashId(EXISTING_SUBJECT_HASH_ID)
                .universityHashId(EXISTING_UNIVERSITY_HASH_ID)
                .courseHashId(EXISTING_COURSE_HASH_ID)
                .reviewText("Lorem ipsum disciplina muito boa!")
                .score(EReviewScore.TWO)
                .build();

        userTestUtils.setUserContextHolder(user);

        assertThrows(AlreadyReviewedByUserException.class, () -> reviewService.send(message));
        List<Review> reviews = reviewService.findAll();
        assertTrue(reviews.size() >= 1 && reviews.size() < 3);
    }
}
