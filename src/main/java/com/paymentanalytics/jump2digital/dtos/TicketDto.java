package com.paymentanalytics.jump2digital.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
public class TicketDto {

    @NotEmpty
    private final String id;

    @NotEmpty
    private final ProductDto product;

    @NotEmpty
    private final Integer amount;

    @Pattern(regexp = "^(VISA|MASTERCARD)$")
    private final String paymentType;

}
