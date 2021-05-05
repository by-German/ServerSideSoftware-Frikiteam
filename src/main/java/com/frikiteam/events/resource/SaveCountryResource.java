package com.frikiteam.events.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCountryResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;


    public String getName() {
        return name;
    }

    public SaveCountryResource setName(String name) {
        this.name = name;
        return this;
    }
}
