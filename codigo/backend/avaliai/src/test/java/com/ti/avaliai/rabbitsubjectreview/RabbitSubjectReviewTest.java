package com.ti.avaliai.rabbitsubjectreview;


import com.ti.avaliai.subjectreview.EReviewScore;
import com.ti.avaliai.subjectreview.SubjectReviewService;
import com.ti.avaliai.subjectreview.dto.CreateSubjectReviewRequestDTO;
import com.ti.avaliai.user.User;
import com.ti.avaliai.utils.UserTestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RabbitSubjectReviewTest {

    private static final String EXISTING_SUBJECT_HASH_ID = "17da1ae431f965d839ec8eb93087fb2b";
    private static final String EXISTING_UNIVERSITY_HASH_ID = "543b45c583bfff6c30e44a751103a24f";
    private static final String EXISTING_COURSE_HASH_ID = "eb5ed7359d0bc0df70e6b7abf8584c5e";

    @Autowired
    private SubjectReviewService subjectReviewService;

    @Test
    void postReview_Success(){

        User user = UserTestUtils.createTestUser();

        CreateSubjectReviewRequestDTO message = CreateSubjectReviewRequestDTO.builder()
                .userHashId(user.getHashId())
                .subjectHashId(EXISTING_SUBJECT_HASH_ID)
                .universityHashId(EXISTING_UNIVERSITY_HASH_ID)
                .courseHashId(EXISTING_COURSE_HASH_ID)
                .reviewText("Lorem ipsum disciplina muito boa!")
                .score(EReviewScore.FIVE)
                .build();

        subjectReviewService.send(message);
    }
}
