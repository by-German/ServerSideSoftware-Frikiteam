package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.Organizer;
import com.frikiteam.events.domain.model.Place;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.repositories.OrganizerRepository;
import com.frikiteam.events.domain.repositories.PlaceRepository;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public Event createEvent(Long organizerId, Long placeId, Event event) {
        Organizer organizer = organizerRepository.findById(organizerId)
                .orElseThrow(() -> new ResourceNotFoundException("Organizer", "id", organizerId));
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new ResourceNotFoundException("Place", "id", placeId));

        // assign organizer to event
        event.setOrganizer(organizer);
        // assign place to event
        event.setPlace(place);

        return eventRepository.save(event);
    }
}
