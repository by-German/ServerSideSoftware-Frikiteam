package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.EventInformation;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface EventInformationService {

    public EventInformation saveEventInformation(EventInformation eventInformation);

    public EventInformation getEventInformationById(Long id);

    public ResponseEntity<?> deleteEventInformation(Long id);

    public ArrayList<EventInformation> getAllEventInformation();

    public EventInformation updateEventInformation(Long id, EventInformation eventInformation);

    List<EventInformation> getEventInformationByEventId(Long eventId);

    EventInformation createEventInformation(Long eventId, EventInformation eventInformation);
}
