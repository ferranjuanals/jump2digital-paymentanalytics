package com.paymentanalytics.jump2digital.model.valueobjects;

import com.paymentanalytics.jump2digital.exceptions.InvalidTypeException;

import java.util.Arrays;

public enum ProductType {
    LAPTOPS,
    PCS,
    PHONES,
    TABLETS,
    OTHER;

    public static ProductType getType(String product) {
        return Arrays.stream(ProductType.values())
                .filter(t -> t.toString().equals(product))
                .findFirst()
                .orElseThrow(() -> new InvalidTypeException("Product type does not exist: " + product));
    }
}
