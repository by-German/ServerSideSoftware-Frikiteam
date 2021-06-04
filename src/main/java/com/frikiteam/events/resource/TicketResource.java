package com.frikiteam.events.resource;

import lombok.Data;

@Data
public class TicketResource {
    private Long id;
    private Double amountPrice;

    private Long customerId;
    private Long eventId;
    private Long offerId;
    private Long paymentMethodId;
}
