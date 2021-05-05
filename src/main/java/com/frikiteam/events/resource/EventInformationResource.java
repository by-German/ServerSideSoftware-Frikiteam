package com.frikiteam.events.resource;


import java.util.Date;

public class EventInformationResource {
    private Long id;
    private String eventDescription;
    private Date startDate;
    private Date endDate;
    private String eventImage;
    private String eventLink;

    public Long getId() {
        return id;
    }

    public EventInformationResource setId(Long eventId) {
        this.id = eventId;
        return this;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public EventInformationResource setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public EventInformationResource setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public EventInformationResource setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getEventImage() {
        return eventImage;
    }

    public EventInformationResource setEventImage(String eventImage) {
        this.eventImage = eventImage;
        return this;
    }

    public String getEventLink() {
        return eventLink;
    }

    public EventInformationResource setEventLink(String eventLink) {
        this.eventLink = eventLink;
        return this;
    }
}
