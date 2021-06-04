package com.frikiteam.events.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SaveTicketResource {
    private Double amountPrice;

    private Long customerId;

    private Long eventId;

    private Long offerId;

    private Long paymentMethodId;
}
