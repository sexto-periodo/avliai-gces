package com.ti.avaliai.rabbitsubjectreview;


import com.ti.avaliai.rabbit.QueueStatusProcessorService;
import com.ti.avaliai.subjectreview.EReviewScore;
import com.ti.avaliai.subjectreview.SubjectReview;
import com.ti.avaliai.subjectreview.SubjectReviewService;
import com.ti.avaliai.subjectreview.dto.CreateSubjectReviewRequestDTO;
import com.ti.avaliai.user.User;
import com.ti.avaliai.user.UserService;
import com.ti.avaliai.utils.UserTestUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RabbitSubjectReviewTest {

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
    private QueueStatusProcessorService queueStatusProcessorService;

    User user1 = null;
    User user2 = null;

    @BeforeAll
    void enqueueSubjectReviewRequest() {

        user1 = userTestUtils.createDefaultTestUser();
        CreateSubjectReviewRequestDTO message1 = CreateSubjectReviewRequestDTO.builder()
                .userHashId(user1.getHashId())
                .subjectHashId(EXISTING_SUBJECT_HASH_ID)
                .universityHashId(EXISTING_UNIVERSITY_HASH_ID)
                .courseHashId(EXISTING_COURSE_HASH_ID)
                .reviewText("Lorem ipsum disciplina muito boa!")
                .score(EReviewScore.FIVE)
                .build();

        subjectReviewService.send(message1);

        user2 = userTestUtils.createRandomTestUser();
        CreateSubjectReviewRequestDTO message2 = CreateSubjectReviewRequestDTO.builder()
                .userHashId(user2.getHashId())
                .subjectHashId(EXISTING_SUBJECT_HASH_ID)
                .universityHashId(EXISTING_UNIVERSITY_HASH_ID)
                .courseHashId(EXISTING_COURSE_HASH_ID)
                .reviewText("Lorem ipsum disciplina muito boa!")
                .score(EReviewScore.FIVE)
                .build();

        subjectReviewService.send(message2);
    }

//    @DisplayName(value = "Teste de Sucesso - Verificar fila vazia")
//    @Test
//    @Order(1)
//    void varifyExistingMessageOnQueue_Success(){
//        assertTrue(queueStatusProcessorService.getSubjectReviewQueueSize() > 0);
//    }

    @DisplayName(value = "Teste de Sucesso - Verificar o consumo e cadastro de avaliação")
    @Test
    @Order(2)
    void consumedReviewRequest_Success() {
        List<SubjectReview> reviews = subjectReviewService.findAll();
        assertEquals(1, reviews.size());
        assertEquals(user1.getEmail(), reviews.get(0).getUser().getEmail());
        assertEquals(user1.getHashId(), reviews.get(0).getUser().getHashId());
        assertEquals(EReviewScore.FIVE, reviews.get(0).getScore());
    }

    @DisplayName(value = "Teste de Sucesso - Verificar fila vazia")
    @Test
    @Order(3)
    void queueEmpty_Success(){
        assertEquals(0, queueStatusProcessorService.getSubjectReviewQueueSize());
    }
}
