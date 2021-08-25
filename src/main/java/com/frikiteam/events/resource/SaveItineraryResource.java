package com.frikiteam.events.resource;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaveItineraryResource {
    private String name;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
}
