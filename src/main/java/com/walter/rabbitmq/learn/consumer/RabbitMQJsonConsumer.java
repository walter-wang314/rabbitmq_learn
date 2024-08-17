package com.walter.rabbitmq.learn.consumer;

import dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeMessage(User userInfo) {
        log.info("Consume JSON from Rabbit MQ: {}", userInfo.toString());
    }

}
