package com.frikiteam.events.resource;

import javax.persistence.Column;

public class EventQualificationResource {

    private Long id;

    private int startQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStartQuantity() {
        return startQuantity;
    }

    public void setStartQuantity(int startQuantity) {
        this.startQuantity = startQuantity;
    }
}
