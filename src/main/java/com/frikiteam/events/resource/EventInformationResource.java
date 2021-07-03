package com.frikiteam.events.resource;


import lombok.Data;

import java.util.Date;

@Data
public class EventInformationResource {
    private Long id;
    private String title;
    private String description;
    private String image;
}
