package com.frikiteam.events.resource;

import lombok.Data;

import java.util.GregorianCalendar;

@Data
public class SaveEventResource {
    private Long id;
    private String logo;
    private String information;
    private String name;
    private Double price;
    private int quantity;
    private Boolean verified;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
}
