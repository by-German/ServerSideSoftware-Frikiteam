package com.frikiteam.events.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SavePlaceResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;
    @Size(max = 200)
    private String reference;
}
