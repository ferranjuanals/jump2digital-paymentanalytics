package com.paymentanalytics.jump2digital.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
public class ProductDto {

    private final String id;

    @NotEmpty
    private final String name;

    @NotEmpty
    private final Double price;

    @Pattern(regexp = "^(LAPTOPS|PCS|PHONES|TABLETS|OTHER)$")
    private final String productType;

    @NotEmpty
    private final String description;

}
