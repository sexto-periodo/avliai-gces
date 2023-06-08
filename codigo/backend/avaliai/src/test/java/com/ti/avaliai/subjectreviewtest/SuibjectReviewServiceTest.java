package com.ti.avaliai.subjectreviewtest;


import com.ti.avaliai.auth.AuthenticationService;
import com.ti.avaliai.subject.SubjectService;
import com.ti.avaliai.subjectreview.EReviewScore;
import com.ti.avaliai.subjectreview.SubjectReviewService;
import com.ti.avaliai.subjectreview.dto.CreateSubjectReviewRequestDTO;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.utils.UserTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ti.avaliai.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuibjectReviewServiceTest {

    //
    // Não permitir  a avaliação de disciplina mais de uma vez pelo mesmo usuártio
    // Calcular a média das notas da disciplina corretamente
    // Calcular upvotes e downvotes corretamente

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
    private SubjectService subjectService;

    @Autowired
    private AuthenticationService authenticationService;

    private void sendGenericReviewMessage(User user, EReviewScore score) {
        CreateSubjectReviewRequestDTO message = CreateSubjectReviewRequestDTO.builder()
                .userHashId(user.getHashId())
                .subjectHashId(EXISTING_SUBJECT_HASH_ID)
                .universityHashId(EXISTING_UNIVERSITY_HASH_ID)
                .courseHashId(EXISTING_COURSE_HASH_ID)
                .reviewText("Lorem ipsum disciplina muito boa!")
                .score(score)
                .build();

        userTestUtils.setUserContextHolder(user);
        subjectReviewService.send(message);
        authenticationService.logout(user);
        authenticationService.clearAllTokens();
        userTestUtils.clearUserContextHolder();
    }

    @DisplayName(value = "Teste de Sucesso - Media de avaliação calulada corretamente")
    @Test
    void reviewSubjectAverageScore_Success() {
        sendGenericReviewMessage(userTestUtils.createDefaultTestUser(), EReviewScore.FIVE);
        sendGenericReviewMessage(userTestUtils.createRandomTestUser(), EReviewScore.ONE);


        assertTrue(subjectReviewService.findAll().size() >= 1);
        assertEquals(3, subjectService.findByHashIdDTO(EXISTING_SUBJECT_HASH_ID).getScore());

    }
}
