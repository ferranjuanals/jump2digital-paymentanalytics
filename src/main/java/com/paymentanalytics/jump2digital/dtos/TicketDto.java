package com.paymentanalytics.jump2digital.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class TicketDto implements Serializable{

    private final UUID id;
    private final ProductDto product;
    private final Integer amount;
    @Pattern(regexp = "^(VISA|MASTERCARD)$")
    private final String paymentType;

}
