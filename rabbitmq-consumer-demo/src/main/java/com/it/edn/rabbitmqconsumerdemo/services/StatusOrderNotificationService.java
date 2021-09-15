package com.it.edn.rabbitmqconsumerdemo.services;

import com.it.edn.rabbitmqconsumerdemo.dtos.OrderStatusDTO;
import com.it.edn.rabbitmqconsumerdemo.dtos.PaymentStatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StatusOrderNotificationService {

    @RabbitListener(queues = "${app.queue.order}")
    void getOrderStatus(OrderStatusDTO payload) {
      log.warn("CONSUMING ORDER MESSAGE - payload:{}", payload);
    }

    @RabbitListener(queues = "${app.queue.payment}")
    void getPaymentStatus(PaymentStatusDTO payload) {
      log.warn("CONSUMING PAYMENT MESSAGE - payload:{}", payload);
    }

}
