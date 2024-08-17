package com.walter.rabbitmq.learn.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.queue.json.name}")
    private String queueJsonName;

    @Value("${rabbitmq.json.routing.key}")
    private String jsonRoutingKey;

    @Bean(name = "queue")
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    public Exchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean(name = "jsonQueue")
    public Queue jsonQueue() {
        return new Queue(queueJsonName);
    }

    @Bean
    public Binding binding() {
        BindingBuilder.DestinationConfigurer destinationConfigurer = BindingBuilder.bind(queue());
        BindingBuilder.GenericExchangeRoutingKeyConfigurer genericExchangeRoutingKeyConfigurer = destinationConfigurer.to(exchange());
        BindingBuilder.GenericArgumentsConfigurer genericArgumentsConfigurer = genericExchangeRoutingKeyConfigurer.with(routingKey);
        Binding binding = genericArgumentsConfigurer.noargs();
        return binding;
    }

    @Bean
    public Binding jsonBinding() {
        BindingBuilder.DestinationConfigurer destinationConfigurer = BindingBuilder.bind(jsonQueue());
        BindingBuilder.GenericExchangeRoutingKeyConfigurer genericExchangeRoutingKeyConfigurer = destinationConfigurer.to(exchange());
        BindingBuilder.GenericArgumentsConfigurer genericArgumentsConfigurer = genericExchangeRoutingKeyConfigurer.with(jsonRoutingKey);
        Binding binding = genericArgumentsConfigurer.noargs();
        return binding;
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean(name = "customized_amqpTemplate")
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
