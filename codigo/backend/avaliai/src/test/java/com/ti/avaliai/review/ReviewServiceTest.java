package com.ti.avaliai.review;


import com.ti.avaliai.global.domain.exceptions.AlreadyReviewedByUserException;
import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.review.dto.CreateReviewRequestDTO;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.user.User;
import com.ti.avaliai.utils.ReviewTestUtils;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
        @Sql(scripts = "classpath:persistence/avaliai/university/before_test_university.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/course/before_test_course.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/academicemail/before_test_academic_email.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(scripts = "classpath:persistence/avaliai/subject/before_test_subject.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
})
public class ReviewServiceTest {

    // Calcular upvotes e downvotes corretamente

    private static final String EXISTING_SUBJECT_HASH_ID = "757b88dbb65e4e13b3319cd8b6a9e2b2";
    private static final String EXISTING_UNIVERSITY_HASH_ID = "1d145f9110ce4e61af7f2363279816f5";
    private static final String EXISTING_COURSE_HASH_ID = "534bf4699af840ffb99f95a1f7d44243";
    private static final String NON_EXISTING_REVIEW_HASH_ID = "acdb9dcd1c4fea1c7c9a6e3889f79df3";

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserTestUtils userTestUtils;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ReviewTestUtils reviewTestUtils;

    @Autowired
    private DataSource dataSource;

    @AfterEach
    public void clearDatabase() {
        TestUtils.clearDatabase(new JdbcTemplate(dataSource));
    }

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
