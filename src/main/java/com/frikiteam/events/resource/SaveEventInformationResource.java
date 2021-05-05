package com.frikiteam.events.resource;

import java.util.Date;

public class SaveEventInformationResource {

    private String eventDescription;
    private Date startDate;
    private Date endDate;
    private String eventImage;
    private String eventLink;

    public String getEventDescription() {
        return eventDescription;
    }

    public SaveEventInformationResource setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public SaveEventInformationResource setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public SaveEventInformationResource setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getEventImage() {
        return eventImage;
    }

    public SaveEventInformationResource setEventImage(String eventImage) {
        this.eventImage = eventImage;
        return this;
    }

    public String getEventLink() {
        return eventLink;
    }

    public SaveEventInformationResource setEventLink(String eventLink) {
        this.eventLink = eventLink;
        return this;
    }
}
