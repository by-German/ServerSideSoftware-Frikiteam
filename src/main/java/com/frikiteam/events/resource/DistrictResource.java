package com.frikiteam.events.resource;

import lombok.Data;

@Data
public class DistrictResource {
    private Long id;
    private String name;
    private CityResource city;
}
