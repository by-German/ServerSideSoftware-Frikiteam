package com.rodrigo.miparte.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveDistrictResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 200)
    private String reference;


    public String getName() {
        return name;
    }

    public SaveDistrictResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public SaveDistrictResource setReference(String reference) {
        this.reference = reference;
        return this;
    }
}
