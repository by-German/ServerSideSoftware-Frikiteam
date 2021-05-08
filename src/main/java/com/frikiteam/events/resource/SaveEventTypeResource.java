package com.frikiteam.events.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveEventTypeResource {
    @NotNull
    @Size(max = 100)
    private String name;

    public String getName() {
        return name;
    }

    public SaveEventTypeResource setName(String name) {
        this.name = name;
        return this;
    }
}
