package com.it.edn.rabbitmqconsumerdemo.services;

import com.it.edn.rabbitmqconsumerdemo.config.MessagingConfig;
import com.it.edn.rabbitmqconsumerdemo.dtos.BankAccountDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Override
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void processAccountValidation(BankAccountDTO account) {
        System.out.println(account);
        System.out.println("$$$$$ consumer already consumes the message $$$$$$");
    }

}
