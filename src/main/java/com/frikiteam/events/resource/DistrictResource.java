package com.frikiteam.events.resource;

public class DistrictResource {
    private Long id;
    private String name;
    private String reference;

    public Long getId() {
        return id;
    }

    public DistrictResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DistrictResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public DistrictResource setReference(String reference) {
        this.reference = reference;
        return this;
    }
}
