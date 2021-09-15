package com.it.edn.rabbitmqproducerdemo.dtos;

import com.it.edn.rabbitmqproducerdemo.enums.PaymentStatus;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusDTO implements Serializable {

    private String orderId;
    private String paymentId;
    private PaymentStatus status;

}
