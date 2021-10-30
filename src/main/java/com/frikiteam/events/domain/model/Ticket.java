package com.frikiteam.events.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amountPrice;

    @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Offer offer;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentMethod paymentMethod;

    public Ticket(Double amountPrice) {
        this.amountPrice = amountPrice;
    }
}


