package com.frikiteam.events.resource;

import javax.persistence.Column;

public class EventQualificationResource {

    private Long id;

    private int starsQuantity;

    private Long customerId;
    private Long EventId;
    private Long ticketId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStarsQuantity() {
        return starsQuantity;
    }

    public void setStarsQuantity(int startQuantity) {
        this.starsQuantity = startQuantity;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getEventId() {
        return EventId;
    }

    public void setEventId(Long eventId) {
        EventId = eventId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
}
