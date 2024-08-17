package com.walter.rabbitmq.learn.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(String message) {
        log.info("Consume from Rabbit MQ: {}", message);
    }

}
