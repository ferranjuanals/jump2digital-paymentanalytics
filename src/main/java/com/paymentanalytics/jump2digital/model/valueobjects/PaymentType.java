package com.paymentanalytics.jump2digital.model.valueobjects;

import com.paymentanalytics.jump2digital.exceptions.InvalidTypeException;

import java.util.Arrays;

public enum PaymentType {
    VISA,
    MASTERCARD;

    public static PaymentType getType(String payment) {
        return Arrays.stream(PaymentType.values())
                .filter(t -> t.toString().equals(payment))
                .findFirst()
                .orElseThrow(() -> new InvalidTypeException("Payment type does not exist: " + payment));
    }
}
