package com.frikiteam.events.resource;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItineraryResource {
    private Long id;
    private String name;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
}
