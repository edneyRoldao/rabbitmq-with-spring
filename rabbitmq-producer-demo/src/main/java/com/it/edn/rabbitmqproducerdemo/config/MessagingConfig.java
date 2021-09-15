package com.it.edn.rabbitmqproducerdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String K1 = "bank_key";
    public static final String BANK_QUEUE = "bank_queue";
    public static final String BANK_EXCHANGE = "bank_exchange";

    public static final String K2 = "status_order_key";
    public static final String STATUS_ORDER_QUEUE = "status_order_queue";
    public static final String ORDER_EXCHANGE = "status_order_exchange";

    public static final String K3 = "status_payment_key";
    public static final String STATUS_PAYMENT_QUEUE = "status_payment_queue";
    public static final String PAYMENT_EXCHANGE = "status_payment_exchange";

    @Bean
    public Queue queue1() { return new Queue(BANK_QUEUE); }

    @Bean
    public TopicExchange exchange1() { return new TopicExchange(BANK_EXCHANGE); }

    @Bean
    public Binding binding1() { return BindingBuilder.bind(queue1()).to(exchange1()).with(K1); }

    @Bean
    public Queue queue2() { return new Queue(STATUS_ORDER_QUEUE); }

    @Bean
    public TopicExchange exchange2() { return new TopicExchange(ORDER_EXCHANGE); }

    @Bean
    public Binding binding2() { return BindingBuilder.bind(queue2()).to(exchange2()).with(K2); }

    @Bean
    public Queue queue3() { return new Queue(STATUS_PAYMENT_QUEUE); }

    @Bean
    public TopicExchange exchange3() { return new TopicExchange(PAYMENT_EXCHANGE); }

    @Bean
    public Binding binding3() { return BindingBuilder.bind(queue3()).to(exchange3()).with(K3); }


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
