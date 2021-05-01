package com.it.edn.rabbitmqproducerdemo.services;

import com.it.edn.rabbitmqproducerdemo.dtos.BankAccountDTO;

public interface BankAccountService {

    void sendBankAccountToValidationQueue(BankAccountDTO account);

}
