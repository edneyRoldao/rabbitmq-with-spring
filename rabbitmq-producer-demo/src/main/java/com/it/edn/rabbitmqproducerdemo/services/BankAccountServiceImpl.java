package com.it.edn.rabbitmqproducerdemo.services;

import com.it.edn.rabbitmqproducerdemo.dtos.BankAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.it.edn.rabbitmqproducerdemo.config.MessagingConfig.EXCHANGE;
import static com.it.edn.rabbitmqproducerdemo.config.MessagingConfig.ROUTING_KEY;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendBankAccountToValidationQueue(BankAccountDTO account) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, account);
        System.out.printf("the message: %s has been sent to exchange: %s and routingKey: %s", account, EXCHANGE, ROUTING_KEY);
    }

}
