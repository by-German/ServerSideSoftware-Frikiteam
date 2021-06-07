package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.Organizer;
import com.frikiteam.events.domain.model.Place;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.repositories.OrganizerRepository;
import com.frikiteam.events.domain.repositories.PlaceRepository;
import com.frikiteam.events.domain.repositories.TagRepository;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private TagRepository tagRepository;

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

    @Override
    public Event updateEvent(Long organizerId, Long eventId, Event event) {
        return organizerRepository.findById(organizerId)
                .map(organizer -> {
                    Event event1 = eventRepository.findById(eventId)
                            .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
                    if (event1.getOrganizer() != organizer)
                        throw new RuntimeException("Event " + eventId + " for Organizer " + organizerId + " not found");
                    event1.setName(event.getName());
                    event1.setQuantity(event.getQuantity());
                    event1.setPrice(event.getPrice());
                    event1.setInformation(event.getInformation());
                    return eventRepository.save(event1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Organizer", "Id", organizerId));
    }

    @Override
    public Event assignEventTag(Long eventId, Long tagId) {
        return tagRepository.findById(tagId)
                .map(tag -> {
                    Event event = eventRepository.findById(eventId)
                            .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
                    event.getTags().add(tag);
                    return eventRepository.save(event);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
    }

    @Override
    public Page<Event> getAllEventsByTagId(Long tagId, Pageable pageable) {
        return tagRepository.findById(tagId)
                .map(tag -> {
                    List<Event> events = tag.getEvents();
                    return new PageImpl<>(events, pageable, events.size());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
    }

    @Override
    public Event unassignEventTag(Long eventId, Long tagId) {
        return tagRepository.findById(tagId)
                .map(tag -> {
                    Event event = eventRepository.findById(eventId)
                            .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
                    event.getTags().remove(tag);
                    return eventRepository.save(event);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));    }

    @Override
    public Event getEventByName(String name) {
        Event result = eventRepository.findByName(name);
        if (result == null)
            throw new ResourceNotFoundException("Event", "Name", name);
        return result;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
