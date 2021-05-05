package com.rodrigo.miparte.resource;

import com.rodrigo.miparte.domain.model.Country;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCityResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;
    @Size(max = 200)
    private String reference;

    public String getName() {
        return name;
    }

    public SaveCityResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public SaveCityResource setReference(String reference) {
        this.reference = reference;
        return this;
    }
}
