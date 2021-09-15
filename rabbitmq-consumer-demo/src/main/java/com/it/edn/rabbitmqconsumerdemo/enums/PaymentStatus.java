package com.it.edn.rabbitmqconsumerdemo.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentStatus {

    WAITING(new String[]{"AUTHORIZING"}),
    PRE_AUTHORIZED(new String[]{"AUTHORIZED", "PRE_AUTHORIZED"}),
    PAID(new String[]{"SUBMITTED_FOR_SETTLEMENT"}),
    ERROR(new String[]{"FAILED"}),
    CANCELLED(new String[]{"CANCELLED"}),
    CANCELLED_BY_GATEWAY(new String[]{"GATEWAY_REJECTED", "PROCESSOR_DECLINED"}),
    REVERSED(new String[]{"VOIDED", "REVERSED"}),
    SETTLEMENT(new String[]{"SETTLED", "SETTLEMENT_CONFIRMED"}),
    SETTLEMENT_REVERSED(new String[]{"SETTLEMENT_REVERSED"});

    private String[] statusTransactions;
    private static Map<String, PaymentStatus> map = new HashMap();
    private static Map<String, PaymentStatus> mapStatusTransactions = new HashMap();

    private PaymentStatus(String... statusTransactions) {
        this.statusTransactions = statusTransactions;
    }

    public static PaymentStatus getStatus(String status) {
        return (PaymentStatus)map.get(status);
    }

    public static PaymentStatus getPaymentByTransaction(String transactionStatus) {
        return (PaymentStatus)mapStatusTransactions.get(transactionStatus);
    }


    static {
        PaymentStatus[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            PaymentStatus paymentStatus = var0[var2];
            map.put(paymentStatus.name().toUpperCase(), paymentStatus);
            map.put(paymentStatus.name().toLowerCase(), paymentStatus);
            String[] var4 = paymentStatus.statusTransactions;
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String status = var4[var6];
                mapStatusTransactions.put(status.toLowerCase(), paymentStatus);
                mapStatusTransactions.put(status.toUpperCase(), paymentStatus);
            }
        }

    }
}
