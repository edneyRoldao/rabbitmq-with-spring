package com.it.edn.rabbitmqconsumerdemo.dtos;

import lombok.*;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusNotificationDTO {

    private String ownId;
    private String orderId;
    private String notificationId;
    private String customerDocument;
    private String orderStatus;
    private String paymentStatus;

}
