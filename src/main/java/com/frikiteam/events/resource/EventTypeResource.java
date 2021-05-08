package com.frikiteam.events.resource;

public class EventTypeResource {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public EventTypeResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EventTypeResource setName(String name) {
        this.name = name;
        return this;
    }
}
