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
    private static final String EXISTING_SUBJECT_HASH_ID = "757b88dbb65e4e13b3319cd8b6a9e2b2";
    private static final String EXISTING_UNIVERSITY_HASH_ID = "1d145f9110ce4e61af7f2363279816f5";
    private static final String EXISTING_COURSE_HASH_ID = "534bf4699af840ffb99f95a1f7d44243";

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
