package com.it.edn.rabbitmqproducerdemo.api;

import com.it.edn.rabbitmqproducerdemo.dtos.BankAccountDTO;
import com.it.edn.rabbitmqproducerdemo.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("digital-bank/api")
public class BankAccountController {

    private final BankAccountService service;

    @PostMapping("create")
    public ResponseEntity<String> createBankAccount(@RequestBody BankAccountDTO account) {
        service.sendBankAccountToValidationQueue(account);
        return ResponseEntity.ok("account has been sent to validation process. You should be wait.");
    }

}
