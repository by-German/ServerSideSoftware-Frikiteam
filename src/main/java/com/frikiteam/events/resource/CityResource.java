package com.frikiteam.events.resource;

public class CityResource {
    private Long id;
    private String name;
    private String reference;

    public Long getId() {
        return id;
    }

    public CityResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public CityResource setReference(String reference) {
        this.reference = reference;
        return this;
    }
}
