package com.ti.avaliai.utils;


import com.ti.avaliai.auth.AuthenticationService;
import com.ti.avaliai.review.EReviewScore;
import com.ti.avaliai.review.ReviewService;
import com.ti.avaliai.review.dto.CreateReviewRequestDTO;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewTestUtils {
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

    public void sendGenericReviewMessage(User user, EReviewScore score) {
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

    public void sendGenericReviewMessage(
            User user,
            EReviewScore score,
            String subjectHashId,
            String universityHashId,
            String courseHashId
    ) {
        CreateReviewRequestDTO message = CreateReviewRequestDTO.builder()
                .userHashId(user.getHashId())
                .subjectHashId(subjectHashId)
                .universityHashId(universityHashId)
                .courseHashId(courseHashId)
                .reviewText("Lorem ipsum disciplina muito boa!")
                .score(score)
                .build();

        userTestUtils.setUserContextHolder(user);
        reviewService.send(message);
        authenticationService.logout(user);
        authenticationService.clearAllTokens();
        userTestUtils.clearUserContextHolder();
    }

    public void createGenericReview(User user, EReviewScore score) {
        CreateReviewRequestDTO message = CreateReviewRequestDTO.builder()
                .userHashId(user.getHashId())
                .subjectHashId(EXISTING_SUBJECT_HASH_ID)
                .universityHashId(EXISTING_UNIVERSITY_HASH_ID)
                .courseHashId(EXISTING_COURSE_HASH_ID)
                .reviewText("Lorem ipsum disciplina muito boa!")
                .score(score)
                .build();

        userTestUtils.setUserContextHolder(user);
        reviewService.create(message);
        authenticationService.logout(user);
        authenticationService.clearAllTokens();
        userTestUtils.clearUserContextHolder();
    }

    public void createGenericReview(User user) {
        EReviewScore score = EReviewScore.FIVE;
        createGenericReview(user, score);
    }
}
