package com.it.edn.rabbitmqconsumerdemo.dtos;

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
