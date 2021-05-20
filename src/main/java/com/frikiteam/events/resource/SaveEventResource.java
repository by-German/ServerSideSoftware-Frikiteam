package com.frikiteam.events.resource;

import lombok.Data;

@Data
public class SaveEventResource {
    private String name;
    private int quantity;
    private Double price;
    private String information;
}
