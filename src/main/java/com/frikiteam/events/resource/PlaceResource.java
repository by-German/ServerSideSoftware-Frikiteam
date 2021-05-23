package com.frikiteam.events.resource;

import lombok.Data;

@Data
public class PlaceResource {
    private Long id;
    private String name;
    private String reference;
    private DistrictResource district;

}
