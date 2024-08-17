package com.walter.rabbitmq.learn.controller;

import com.walter.rabbitmq.learn.publisher.RabbitMQJsonProducer;
import dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    final RabbitMQJsonProducer rabbitMQJsonProducer;

    public MessageJsonController(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody User userInfo) {
        rabbitMQJsonProducer.sendJsonMessage(userInfo);
        return ResponseEntity.ok("JSON Message sent to Rabbit MQ: " + userInfo);
    }
}
