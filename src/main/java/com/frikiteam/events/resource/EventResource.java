package com.frikiteam.events.resource;

import lombok.Data;

@Data
public class EventResource {
    private Long id;
    private String name;
    private int quantity;
    private Double price;
    private String information;
    private OrganizerResource organizer;
    private EventTypeResource eventType;
    private PlaceResource place;
    private EventInformationResource eventInformation;
}
