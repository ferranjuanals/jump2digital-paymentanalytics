package com.paymentanalytics.jump2digital.model.entities;

import com.paymentanalytics.jump2digital.model.valueobjects.ProductType;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Number price;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductType productType;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "product")
    private List<Ticket> tickets;

}
