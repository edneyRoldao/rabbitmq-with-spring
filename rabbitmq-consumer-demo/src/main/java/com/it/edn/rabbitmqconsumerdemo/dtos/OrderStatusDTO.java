package com.it.edn.rabbitmqconsumerdemo.dtos;

import com.it.edn.rabbitmqconsumerdemo.enums.OrderStatus;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDTO implements Serializable {
    private static final long serialVersionUID = -1526925773020002157L;

    private String orderId;
    private OrderStatus status;

}
