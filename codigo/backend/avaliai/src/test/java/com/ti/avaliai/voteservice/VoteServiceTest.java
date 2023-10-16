package com.ti.avaliai.voteservice;


import com.ti.avaliai.auth.AuthenticationService;
import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.review.EReviewScore;
import com.ti.avaliai.review.Review;
import com.ti.avaliai.review.ReviewService;
import com.ti.avaliai.review.dto.ReviewDTO;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.utils.ReviewTestUtils;
import com.ti.avaliai.utils.TestUtils;
import com.ti.avaliai.utils.UserTestUtils;
import com.ti.avaliai.vote.Vote;
import com.ti.avaliai.vote.VoteService;
import com.ti.avaliai.vote.dto.VoteRequestDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
public class VoteServiceTest {

    private static final String EXISTING_DEFAULT_USER_EMAIL = "testuser@sga.pucminas.br";
    private static final String EXISTING_RANDOM_USER_EMAIL = "randomuser@sga.pucminas.br";

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserTestUtils userTestUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ReviewTestUtils reviewTestUtils;

    @Autowired
    private VoteService voteService;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setup() {
        reviewTestUtils.createGenericReview(userTestUtils.createDefaultTestUser(), EReviewScore.FIVE);
        reviewTestUtils.createGenericReview(userTestUtils.createRandomTestUser(), EReviewScore.ONE);
    }

    @AfterEach
    public void clearDatabase() {
        TestUtils.clearDatabase(new JdbcTemplate(dataSource));
    }


    public void upvoteReview(User user, Review review) {

        VoteRequestDTO request = VoteRequestDTO.builder()
                .isVoted(true)
                .voteUpDown(true)
                .reviewHashId(review.getHashId())
                .build();

        userTestUtils.setUserContextHolder(user);
        voteService.vote(request);
        authenticationService.logout(user);
        authenticationService.clearAllTokens();
        userTestUtils.clearUserContextHolder();
    }

    public void sendVoteUpdate(User user, VoteRequestDTO request) {

        userTestUtils.setUserContextHolder(user);
        voteService.vote(request);
        authenticationService.logout(user);
        authenticationService.clearAllTokens();
        userTestUtils.clearUserContextHolder();
    }

    @DisplayName(value = "Teste de Sucesso - Votar em uma avaliação corretamente")
    @Test
    void createVote_Success() {


        List<Review> reviews = reviewService.findAll();
        Review review = reviews.get(0);

        User user = userService.findByEmail(EXISTING_DEFAULT_USER_EMAIL);
        upvoteReview(user, review);
        Vote vote = voteService.findByReviewAndUser(review, user);

        assertTrue(vote.isVoted());
        assertTrue(vote.isVoteUpDown());
    }

    @DisplayName(value = "Teste de Sucesso - Remover voto corretamente")
    @Test
    void removeVote_Success() {

        List<Review> reviews = reviewService.findAll();
        Review review = reviews.get(0);
        User user = userService.findByEmail(EXISTING_DEFAULT_USER_EMAIL);
        upvoteReview(user, review);
        Vote actualVote = voteService.findByReviewAndUser(review, user);

        VoteRequestDTO voteRequestUpdate = VoteRequestDTO
                .builder()
                .reviewHashId(review.getHashId())
                .voteUpDown(actualVote.isVoteUpDown())
                .isVoted(false)
                .build();

        sendVoteUpdate(user, voteRequestUpdate);

        Vote voteUpdated = voteService.findByReviewAndUser(review, user);

        assertFalse(voteUpdated.isVoted());
        assertTrue(voteUpdated.isVoteUpDown());
    }

    @DisplayName(value = "Teste de Sucesso - Calcular empate de votos corretamente")
    @Test
    void calculateReviewVotesZero_Success() {

        List<Review> reviews = reviewService.findAll();
        Review review = reviews.get(0);
        User user1 = userService.findByEmail(EXISTING_DEFAULT_USER_EMAIL);

        VoteRequestDTO voteRequest1 = VoteRequestDTO.builder()
                .reviewHashId(review.getHashId())
                .voteUpDown(true)
                .isVoted(true)
                .build();
        sendVoteUpdate(user1, voteRequest1);

        User user2 = userService.findByEmail(EXISTING_RANDOM_USER_EMAIL);
        VoteRequestDTO voteRequest2 = VoteRequestDTO.builder()
                .reviewHashId(review.getHashId())
                .voteUpDown(false)
                .isVoted(true)
                .build();
        sendVoteUpdate(user2, voteRequest2);

        reviews = reviewService.findAll();
        List<Review> reviewsUpdated = reviewService.findAll();

        int voteCount = voteService.countReviewVotes(reviewsUpdated.get(0));

        assertEquals(0, voteCount);
    }

    @DisplayName(value = "Teste de Sucesso - Calcular votos positivos corretamente")
    @Test
    void calculateReviewVotesPositive_Success() {

        List<Review> reviews = reviewService.findAll();
        Review review = reviews.get(0);
        User user1 = userService.findByEmail(EXISTING_DEFAULT_USER_EMAIL);

        VoteRequestDTO voteRequest1 = VoteRequestDTO.builder()
                .reviewHashId(review.getHashId())
                .voteUpDown(true)
                .isVoted(true)
                .build();
        sendVoteUpdate(user1, voteRequest1);

        User user2 = userService.findByEmail(EXISTING_RANDOM_USER_EMAIL);
        VoteRequestDTO voteRequest2 = VoteRequestDTO.builder()
                .reviewHashId(review.getHashId())
                .voteUpDown(true)
                .isVoted(true)
                .build();
        sendVoteUpdate(user2, voteRequest2);

        reviews = reviewService.findAll();
        List<Review> reviewsUpdated = reviewService.findAll();

        int voteCount = voteService.countReviewVotes(reviewsUpdated.get(0));

        assertEquals(2, voteCount);
    }

    @DisplayName(value = "Teste de Sucesso - Calcular votos negativos corretamente")
    @Test
    void calculateReviewVotesNegative_Success() {

        List<Review> reviews = reviewService.findAll();
        Review review = reviews.get(0);
        User user1 = userService.findByEmail(EXISTING_DEFAULT_USER_EMAIL);

        VoteRequestDTO voteRequest1 = VoteRequestDTO.builder()
                .reviewHashId(review.getHashId())
                .voteUpDown(false)
                .isVoted(true)
                .build();
        sendVoteUpdate(user1, voteRequest1);

        User user2 = userService.findByEmail(EXISTING_RANDOM_USER_EMAIL);
        VoteRequestDTO voteRequest2 = VoteRequestDTO.builder()
                .reviewHashId(review.getHashId())
                .voteUpDown(false)
                .isVoted(true)
                .build();
        sendVoteUpdate(user2, voteRequest2);

        reviews = reviewService.findAll();
        List<Review> reviewsUpdated = reviewService.findAll();

        int voteCount = voteService.countReviewVotes(reviewsUpdated.get(0));

        assertEquals(-2, voteCount);
    }

    @DisplayName(value = "Teste de Falha - Busca por voto inexistente")
    @Test
    void notFoundReviewByHashId_Failure() {
        User user1 = userService.findByEmail(EXISTING_DEFAULT_USER_EMAIL);
        User user2 = userService.findByEmail(EXISTING_RANDOM_USER_EMAIL);
        userTestUtils.setUserContextHolder(user2);
        List<ReviewDTO> reviews = reviewService.findAllByLoggedUser();
        Review review = reviewService.findByHashId(reviews.get(0).getHashId());
        assertThrows(EntityNotFoundException.class, () -> voteService.findByReviewAndUser(review, user1));
    }
}
