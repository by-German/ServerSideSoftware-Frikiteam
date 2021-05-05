package com.frikiteam.events.resource;

public class PlaceResource {
    private Long id;
    private String name;
    private String reference;

    public Long getId() {
        return id;
    }

    public PlaceResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlaceResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public PlaceResource setReference(String reference) {
        this.reference = reference;
        return this;
    }
}
