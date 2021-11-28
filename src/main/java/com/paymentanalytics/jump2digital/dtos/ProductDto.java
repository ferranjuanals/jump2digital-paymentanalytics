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
public class ProductDto implements Serializable {

    private  UUID id;
    private  String name;
    private  Double price;
    @Pattern(regexp = "^(LAPTOPS|PCS|PHONES|TABLETS|OTHER)$")
    private  String productType;
    private  String description;

}
