package com.ti.avaliai.rabbit.consumer;

import com.ti.avaliai.subjectreview.SubjectReviewService;
import com.ti.avaliai.subjectreview.dto.CreateSubjectReviewRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log4j2
@Component
public class RabbitMQConsumer {

    @Autowired
    private SubjectReviewService subjectReviewService;

    @RabbitListener(queues = {"${rabbitmq.queue}"})
    public void receive(@Payload CreateSubjectReviewRequestDTO reviewMessage) {

        log.info("Mensagem de avaliação recebida com sucesso: "+ LocalDateTime.now());

        subjectReviewService.create(reviewMessage);

        log.info("Avaliação consumida com sucesso: "+ LocalDateTime.now());
    }



}