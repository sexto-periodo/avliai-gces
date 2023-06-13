package com.ti.avaliai.review;


import com.ti.avaliai.auth.AuthenticationService;
import com.ti.avaliai.global.domain.exceptions.AlreadyReviewedByUserException;
import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.review.dto.CreateReviewRequestDTO;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.utils.ReviewTestUtils;
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
public class ReviewServiceTest {

    // Calcular upvotes e downvotes corretamente

    private static final String EXISTING_SUBJECT_HASH_ID = "17da1ae431f965d839ec8eb93087fb2b";
    private static final String EXISTING_UNIVERSITY_HASH_ID = "543b45c583bfff6c30e44a751103a24f";
    private static final String EXISTING_COURSE_HASH_ID = "eb5ed7359d0bc0df70e6b7abf8584c5e";
    private static final String NON_EXISTING_REVIEW_HASH_ID = "acdb9dcd1c4fea1c7c9a6e3889f79df3";
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

    @Autowired
    private ReviewTestUtils reviewTestUtils;




    @DisplayName(value = "Teste de Sucesso - Media de avaliação calulada corretamente")
    @Test
    void reviewSubjectAverageScore_Success() {
        reviewTestUtils.createGenericReview(userTestUtils.createRandomTestUser(), EReviewScore.ONE);
        reviewTestUtils.createGenericReview(userTestUtils.createDefaultTestUser(), EReviewScore.FIVE);


        assertTrue(reviewService.findAll().size() >= 1);
        assertEquals(3, subjectService.findByHashIdDTO(EXISTING_SUBJECT_HASH_ID).getScore());
    }

    @DisplayName(value = "Teste de Falha - Não permitir a avaliação da mesma disciplina pelo mesmo usuário")
    @Test
    void alreadyReviewedByUser_Failure() {
        User user = userTestUtils.createDefaultTestUser();
        reviewTestUtils.createGenericReview(user, EReviewScore.FIVE);
        reviewTestUtils.createGenericReview(userTestUtils.createRandomTestUser(), EReviewScore.ONE);

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

    @DisplayName(value = "Teste de Falha - Busca de avaliação por hashId inexistente")
    @Test
    void notFoundReviewByHashId_Failure() {
        assertThrows(EntityNotFoundException.class, () -> reviewService.findByHashId(NON_EXISTING_REVIEW_HASH_ID));
    }

}
