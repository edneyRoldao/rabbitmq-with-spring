package com.it.edn.rabbitmqconsumerdemo.services;

import com.it.edn.rabbitmqconsumerdemo.dtos.BankAccountDTO;

public interface BankAccountService {

    void processAccountValidation(BankAccountDTO account);

}
