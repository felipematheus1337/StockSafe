package com.stocksafe.model.enums;

public enum BoxCapacityStatus {

    OVER_CAPACITY("A capacidade máxima da caixa foi atingida."),
    EXCEEDS_REMAINING("O peso dos itens excede a capacidade restante."),
    VALID("Peso válido para adição.");

    private final String message;

    BoxCapacityStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
