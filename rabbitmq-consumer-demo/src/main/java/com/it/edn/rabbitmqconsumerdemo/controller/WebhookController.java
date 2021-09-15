package com.it.edn.rabbitmqconsumerdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.edn.rabbitmqconsumerdemo.dtos.OrderStatusNotificationDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@RestController
@RequestMapping("webhook")
public class WebhookController {

    private static final String SECRET_KEY = "STO-40e14edf-6212-4bc2-8c64-2eee4233b08e";

    @PostMapping
    public ResponseEntity<?> statusOrderWebhook(@RequestHeader("abasteceai-signature") String signature,
                                                @RequestBody OrderStatusNotificationDTO request) {
        log.warn("Webhook called - request:{}", request);

        String ownSignature = generateHashCodeWithMacSha256(SECRET_KEY, request);

        log.error("signature:{} - ownSignature:{}", signature, ownSignature);

        return signature.equals(ownSignature) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    private String generateHashCodeWithMacSha256(String secretKey, Object content) {
        String contentAsString = objectToJsonAsString(content);
        return generateMacSha256(secretKey, contentAsString);
    }

    private String generateMacSha256(String secretKey, String requestBody) {
        try {
            String algorithm = "HmacSHA256";
            Mac mac = Mac.getInstance(algorithm);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), algorithm);
            mac.init(secretKeySpec);
            byte[] hMacSha256 = mac.doFinal(requestBody.getBytes(StandardCharsets.UTF_8));

            return String.format("%032x", new BigInteger(1, hMacSha256));

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
    }

    @SneakyThrows
    public static String objectToJsonAsString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
