package com.frikiteam.events.resource;

import lombok.Data;

import java.util.GregorianCalendar;

@Data
public class EventResource {
    private Long id;
    private String logo;
    private String information;
    private String name;
    private Double price;
    private int quantity;
    private int sold;
    private Boolean verified;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;

    private Long eventTypeId;
    private Long OrganizerId;
    private Long PlaceId;
}