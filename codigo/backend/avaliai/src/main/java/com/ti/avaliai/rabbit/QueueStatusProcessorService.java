package com.ti.avaliai.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class QueueStatusProcessorService {
    @Autowired
    private RabbitAdmin admin;
    @Autowired
    private List<Queue> rabbitQueues;

    @Value("${rabbitmq.queue}")
    private String subjectReviewQueue;

    public void getCounts() {
        Properties props;
        Integer messageCount;
        for (Queue queue : rabbitQueues) {
            props = admin.getQueueProperties(queue.getName());
            messageCount = Integer.parseInt(props.get("QUEUE_MESSAGE_COUNT").toString());
            System.out.println(queue.getName() + " has " + messageCount + " messages");
        }
    }

    public int getSubjectReviewQueueSize(){
        Properties props;
        Integer messageCount;
        props = admin.getQueueProperties(subjectReviewQueue);
        int count = Integer.parseInt(props.get("QUEUE_MESSAGE_COUNT").toString());
        return count;
    }
}