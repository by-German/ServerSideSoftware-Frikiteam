package com.frikiteam.events.resource;

public class TypeEventResource {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public TypeEventResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TypeEventResource setName(String name) {
        this.name = name;
        return this;
    }
}
