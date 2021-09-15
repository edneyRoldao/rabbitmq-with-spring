package com.it.edn.rabbitmqproducerdemo.enums;

public enum GenericStatus {

    PENDING("Pendente"),
    CANCELED("Cancelado"),
    FINISHED("Finalizado"),
    SETTLEMENT("Liquidado"),
    REFUNDED("Reembolsado"),
    ERROR("Erro"),
    DENIED("Negado");

    private String description;

    private GenericStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isCanceled() {
        return CANCELED.equals(this);
    }

    public boolean isFinished() {
        return FINISHED.equals(this);
    }

    public boolean isNotPending() {
        return !PENDING.equals(this);
    }

    public boolean isPending() {
        return PENDING.equals(this);
    }

    public boolean isError() {
        return ERROR.equals(this);
    }

}
