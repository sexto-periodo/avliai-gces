package com.ti.avaliai.rabbit.producer;

import com.ti.avaliai.review.dto.CreateReviewRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RabbitMQProducer {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    public void sendReviewMessage(CreateReviewRequestDTO message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info("Avaliação postada com sucesso na Fila.");
    }

}
