package com.ti.avaliai.subjectreviewtest;


import com.ti.avaliai.subjectreview.SubjectReviewService;
import com.ti.avaliai.utils.UserTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ti.avaliai.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuibjectReviewServiceTest {

    //
    // Não permitir  a avaliação de disciplina mais de uma vez pelo mesmo usuártio
    // Calcular a média das notas da disciplina corretamente
    // Calcular upvotes e downvotes corretamente

    @Autowired
    private SubjectReviewService subjectReviewService;

    @Autowired
    private UserTestUtils userTestUtils;

//    @DisplayName(value = "Teste de Sucesso - Avaliar disciplina corretamente")
//    @Test
//    void reviewSubject_Success() {
//        User user = userTestUtils.createDefaultTestUser();
//
////        subjectReviewService.send();
//
//    }
}
