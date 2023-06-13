package com.ti.avaliai.voteservice;


import com.ti.avaliai.auth.AuthenticationService;
import com.ti.avaliai.global.domain.exceptions.EntityNotFoundException;
import com.ti.avaliai.review.EReviewScore;
import com.ti.avaliai.review.Review;
import com.ti.avaliai.review.ReviewService;
import com.ti.avaliai.review.dto.ReviewDTO;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.utils.ReviewTestUtils;
import com.ti.avaliai.utils.UserTestUtils;
import com.ti.avaliai.vote.Vote;
import com.ti.avaliai.vote.VoteService;
import com.ti.avaliai.vote.dto.VoteRequestDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VoteServiceTest {

    private static final String EXISTING_SUBJECT_HASH_ID = "17da1ae431f965d839ec8eb93087fb2b";
    private static final String EXISTING_UNIVERSITY_HASH_ID = "543b45c583bfff6c30e44a751103a24f";
    private static final String EXISTING_COURSE_HASH_ID = "eb5ed7359d0bc0df70e6b7abf8584c5e";
    private static final String EXISTING_DEFAULT_USER_EMAIL = "testuser@sga.pucminas.br";
    private static final String EXISTING_RANDOM_USER_EMAIL = "randomuser@sga.pucminas.br";

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

    @Autowired
    private VoteService voteService;

    @BeforeAll
    void setup() {
        reviewTestUtils.createGenericReview(userTestUtils.createDefaultTestUser(), EReviewScore.FIVE);
        reviewTestUtils.createGenericReview(userTestUtils.createRandomTestUser(), EReviewScore.ONE);
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
