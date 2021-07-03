package com.frikiteam.events.resource;

import lombok.Data;

@Data
public class SaveEventQualificationResource {
    private int starsQuantity;
    private Long customerId;
    private Long ticketId;
}
