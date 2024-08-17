package com.walter.rabbitmq.learn.publisher;

import dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.json.routing.key}")
    private String routingKey;

    private final AmqpTemplate amqpTemplate;

    public RabbitMQJsonProducer(@Qualifier(value = "customized_amqpTemplate") AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendJsonMessage(User userInfo) {
        log.info("Json Message Sent -> {}", userInfo.toString());
        amqpTemplate.convertAndSend(exchangeName, routingKey, userInfo);
    }
}
