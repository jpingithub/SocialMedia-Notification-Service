package com.rb.email.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.messaging.exchange}")
    private String EXCHANGE_NAME;
    @Value("${spring.messaging.queue}")
    private String QUEUE;
    @Value("${spring.messaging.routing-key}")
    private String ROUTING_KEY;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue followQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public Binding followBinding(Queue followQueue, TopicExchange exchange) {
        return BindingBuilder.bind(followQueue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
