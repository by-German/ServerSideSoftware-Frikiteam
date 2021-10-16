package com.frikiteam.events.resource;

import com.frikiteam.events.domain.model.Customer;
import lombok.Data;

@Data
public class PaymentIntentDto {
    private String description;
    private int amount;
    private String currency; // usd
    private Long customerId;
    private Long eventId;
    private String email;
}
