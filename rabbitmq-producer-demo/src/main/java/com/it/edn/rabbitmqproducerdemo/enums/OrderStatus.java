package com.it.edn.rabbitmqproducerdemo.enums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    PENDING("Aguardando autorização do cliente", GenericStatus.PENDING, new String[]{"PRE_AUTHORIZATION_WAITING", "PRE_AUTHORIZED_ERROR", "CANCELED_BY_STORE", "EXPIRED"}),
    PRE_AUTHORIZATION_WAITING("Aguardando confirmação da pre-autorizacao", GenericStatus.PENDING, new String[]{"PRE_AUTHORIZED_ERROR", "CANCELED_BY_GATEWAY", "CANCELED_BY_STORE", "CANCELED_BY_BACKOFFICE", "CANCELED_BY_PRE_AUTHORIZATION_TIMEOUT", "PRE_AUTHORIZED_BY_GATEWAY"}),
    PRE_AUTHORIZED_BY_GATEWAY("Pre-autorizado pelo gateway", GenericStatus.PENDING, new String[]{"CANCELED_BY_CUSTOMER", "EXPIRED", "CONFIRMATION_WAITING"}),
    PRE_AUTHORIZED_ERROR("Erro na pre-autorizacao", GenericStatus.CANCELED, new String[0]),
    CANCELED_BY_GATEWAY("Cancelado pelo gateway", GenericStatus.CANCELED, new String[0]),
    CANCELED_BY_STORE("Cancelado pela loja", GenericStatus.CANCELED, new String[0]),
    CANCELED_BY_BACKOFFICE("Cancelado pelo backoffice", GenericStatus.CANCELED, new String[0]),
    CANCELED_BY_CUSTOMER("Cancelado pelo usuário", GenericStatus.CANCELED, new String[0]),
    CANCELED_BY_PRE_AUTHORIZATION_TIMEOUT("Cancelado por falta de resposta na pre-autorizacao", GenericStatus.CANCELED, new String[0]),
    EXPIRED("Expirado", GenericStatus.CANCELED, new String[0]),
    REVERSED("Revertida", GenericStatus.CANCELED, new String[0]),
    CONFIRMATION_WAITING("Aguardando confirmando da autorizacao", GenericStatus.FINISHED, new String[]{"CONFIRMED", "REVERSED"}),
    CONFIRMED("Confirmado", GenericStatus.FINISHED, new String[]{"CANCELED_BY_STORE", "CANCELED_BY_BACKOFFICE", "REVERSED"});

    private final String description;
    private final GenericStatus genericStatus;
    private final String[] nextStatus;
    private static final Map<String, OrderStatus> cancellableStatus = new HashMap();
    private static final Map<String, OrderStatus> canceledStatus = new HashMap();
    private static final Map<OrderStatus, Map<String, OrderStatus>> nextStatusMap = new HashMap();

    private OrderStatus(String description, GenericStatus genericStatus, String... nextStatus) {
        this.description = description;
        this.genericStatus = genericStatus;
        this.nextStatus = nextStatus;
    }

    public String getDescription() {
        return this.description;
    }

    public GenericStatus getGenericStatus() {
        return this.genericStatus;
    }

    public boolean isAbleToCancel() {
        return cancellableStatus.containsKey(this.name());
    }

    public boolean isUnableToCancel() {
        return !this.isAbleToCancel();
    }

    public boolean isPending() {
        return PENDING.equals(this);
    }

    public boolean isExpired() {
        return EXPIRED.equals(this);
    }

    public boolean isCanceled() {
        return canceledStatus.containsKey(this.name());
    }

    public boolean isCanceledByBackoffice() {
        return CANCELED_BY_BACKOFFICE.equals(this);
    }

    public boolean isNotPending() {
        return !PENDING.equals(this);
    }

    public boolean isAbleToExpire() {
        return this.getGenericStatus().isPending();
    }

    public boolean isUnableToExpire() {
        return !this.isAbleToExpire();
    }

    public boolean isPreAuthorizedByGateway() {
        return PRE_AUTHORIZED_BY_GATEWAY.equals(this);
    }

    public boolean isAbleToConfirm() {
        return CONFIRMATION_WAITING.equals(this);
    }

    public boolean hasPaymentError() {
        return PRE_AUTHORIZED_ERROR.equals(this) || CANCELED_BY_GATEWAY.equals(this);
    }

    public boolean isPreAuthorizationWaiting() {
        return PRE_AUTHORIZATION_WAITING.equals(this);
    }

    public boolean isConfirmationWaiting() {
        return CONFIRMATION_WAITING.equals(this);
    }

    public boolean isValidNextStatus(OrderStatus orderStatus) {
        return ((Map)nextStatusMap.get(this)).containsKey(orderStatus.name());
    }

    public Map<String, OrderStatus> getNextStatus() {
        return (Map)nextStatusMap.get(this);
    }

    public static Collection<OrderStatus> getStatuses(GenericStatus genericStatus) {
        ArrayList<OrderStatus> statuses = new ArrayList(values().length);
        OrderStatus[] var2 = values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            OrderStatus orderStatus = var2[var4];
            if (orderStatus.getGenericStatus().equals(genericStatus)) {
                statuses.add(orderStatus);
            }
        }

        return statuses;
    }

    public boolean isReversed() {
        return REVERSED.equals(this);
    }

    public boolean isConfirmed() {
        return CONFIRMED.equals(this);
    }

    static {
        cancellableStatus.put(PENDING.name(), PENDING);
        cancellableStatus.put(CANCELED_BY_GATEWAY.name(), PENDING);
        cancellableStatus.put(PRE_AUTHORIZED_ERROR.name(), PENDING);
        cancellableStatus.put(PRE_AUTHORIZATION_WAITING.name(), PENDING);
        cancellableStatus.put(PRE_AUTHORIZED_BY_GATEWAY.name(), PRE_AUTHORIZED_BY_GATEWAY);
        cancellableStatus.put(CONFIRMATION_WAITING.name(), PENDING);
        cancellableStatus.put(CONFIRMED.name(), CONFIRMED);
        canceledStatus.put(PRE_AUTHORIZED_ERROR.name(), PRE_AUTHORIZED_ERROR);
        canceledStatus.put(CANCELED_BY_GATEWAY.name(), CANCELED_BY_GATEWAY);
        canceledStatus.put(CANCELED_BY_STORE.name(), CANCELED_BY_STORE);
        canceledStatus.put(CANCELED_BY_BACKOFFICE.name(), CANCELED_BY_BACKOFFICE);
        canceledStatus.put(CANCELED_BY_CUSTOMER.name(), CANCELED_BY_CUSTOMER);
        canceledStatus.put(CANCELED_BY_PRE_AUTHORIZATION_TIMEOUT.name(), CANCELED_BY_PRE_AUTHORIZATION_TIMEOUT);
        canceledStatus.put(EXPIRED.name(), EXPIRED);
        canceledStatus.put(REVERSED.name(), REVERSED);
        OrderStatus[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            OrderStatus value = var0[var2];
            Map<String, OrderStatus> nextStatus = new HashMap();
            String[] var5 = value.nextStatus;
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String valueNext = var5[var7];
                nextStatus.put(valueNext, valueOf(valueNext));
            }

            nextStatusMap.put(value, nextStatus);
        }

    }
}
