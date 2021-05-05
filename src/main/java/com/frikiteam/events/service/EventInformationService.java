package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.EventInformation;

import java.util.ArrayList;
import java.util.Optional;


public interface EventInformationService {

    public EventInformation saveEventInformation(EventInformation eventInformation);

    public Optional<EventInformation> getEventInformationById(Long id);

    public boolean deleteEventInformation(Long id);

    public ArrayList<EventInformation> getAllEventInformation();

    public EventInformation updateEventInformation(Long id, EventInformation eventInformation);
}
