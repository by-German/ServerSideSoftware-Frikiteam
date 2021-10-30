package com.frikiteam.events.domain.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="events_information")
public class EventInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    private String description;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Event event;

    public EventInformation(){}

    public EventInformation(@NotNull String title, String eventDescription, String eventImage) {
        this.description = eventDescription;
        this.image = eventImage;
        this.title = title;
    }
}