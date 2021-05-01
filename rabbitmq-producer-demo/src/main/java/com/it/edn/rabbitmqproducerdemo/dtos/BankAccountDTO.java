package com.it.edn.rabbitmqproducerdemo.dtos;

import lombok.*;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {

    private String name;
    private String accountID;

}
