package com.frikiteam.events.resource;

import lombok.Data;

@Data
public class CityResource {
    private Long id;
    private String name;
    private CountryResource country;
}
