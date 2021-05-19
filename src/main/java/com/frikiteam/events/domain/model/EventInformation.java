package com.frikiteam.events.domain.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="events_information")
public class EventInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String description;

    @Column(name = "start_date")
    //@NotNull
    private Date startDate;

    @Column(name = "end_date")
    //@NotNull
    private Date endDate;

    @NotNull
    @Lob
    private String image;

    @NotNull
    @Lob
    private String link;

    public EventInformation(){}

    public EventInformation(@NotNull String eventDescription, @NotNull Date startDate, @NotNull Date endDate, @NotNull String eventImage, @NotNull String eventLink) {
        this.description = eventDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = eventImage;
        this.link = eventLink;
    }
}