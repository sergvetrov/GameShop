package org.example.enums;

public enum CreditCards {
    VISA("Visa"),
    MASTERCARD("Mastercard");

    private final String type;

    CreditCards(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
