package com.it.edn.rabbitmqconsumerdemo.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Value("${app.queue.bank}") private String bankQueue;
    @Value("${app.queue.order}") private String statusOrderQueue;
    @Value("${app.queue.payment}") private String statusPaymentQueue;

    @Bean public Queue queue1() {
        return new Queue(bankQueue);
    }
    @Bean public Queue queue2() {return new Queue(statusOrderQueue);}
    @Bean public Queue queue3() {return new Queue(statusPaymentQueue);}


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
