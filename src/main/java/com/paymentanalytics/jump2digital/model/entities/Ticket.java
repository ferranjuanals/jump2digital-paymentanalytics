package com.paymentanalytics.jump2digital.model.entities;

import com.paymentanalytics.jump2digital.model.valueobjects.PaymentType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue
    private UUID id;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "amount", nullable = false)
    private Number amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

}
