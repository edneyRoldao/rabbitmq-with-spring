package com.it.edn.rabbitmqproducerdemo.api;

import com.it.edn.rabbitmqproducerdemo.dtos.OrderStatusDTO;
import com.it.edn.rabbitmqproducerdemo.dtos.PaymentStatusDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.it.edn.rabbitmqproducerdemo.config.MessagingConfig.K2;
import static com.it.edn.rabbitmqproducerdemo.config.MessagingConfig.K3;
import static com.it.edn.rabbitmqproducerdemo.config.MessagingConfig.ORDER_EXCHANGE;
import static com.it.edn.rabbitmqproducerdemo.config.MessagingConfig.PAYMENT_EXCHANGE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("ecommerce/notify")
public class StatusNotificationController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("order-status")
    public ResponseEntity<?> publishStatusOrderNotification(@RequestBody OrderStatusDTO request) {
        rabbitTemplate.convertAndSend(ORDER_EXCHANGE, K2, request);
        log.warn("the message:{} has been sent to exchange:{} and routingKey:{}", request, ORDER_EXCHANGE, K2);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("payment-status")
    public ResponseEntity<?> publishStatusPaymentNotification(@RequestBody PaymentStatusDTO request) {
        rabbitTemplate.convertAndSend(PAYMENT_EXCHANGE, K3, request);
        log.warn("the message:{} has been sent to exchange:{} and routingKey:{}", request, PAYMENT_EXCHANGE, K3);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
