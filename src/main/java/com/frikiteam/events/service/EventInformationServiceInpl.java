package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.domain.repositories.EventInformationRepository;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.service.EventInformationService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EventInformationServiceInpl implements EventInformationService {
    @Autowired
    private EventInformationRepository eventsInformationRepository;
    @Autowired
    private EventRepository eventRepository;


    @Override
    public ArrayList<EventInformation> getAllEventInformation(){
        return (ArrayList<EventInformation>) eventsInformationRepository.findAll();
    }

    @Override
    public EventInformation saveEventInformation(EventInformation eventInformation) {
        return eventsInformationRepository.save(eventInformation);
    }

    @Override
    public EventInformation getEventInformationById(Long id) {
        return eventsInformationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("EventInformation", "id", id));
    }

    @Override
    public ResponseEntity<?> deleteEventInformation(Long id) {

        EventInformation eventInformation = eventsInformationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EventInformation", "id", id));
        eventsInformationRepository.delete(eventInformation);
        return ResponseEntity.ok().build();
    }

    @Override
    public EventInformation updateEventInformation(Long id, EventInformation eventInformation) {
        EventInformation existed = eventsInformationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EventInformation", "id", id));

            existed.setId(eventInformation.getId());
            existed.setImage(eventInformation.getImage());
            existed.setLink(eventInformation.getLink());
            existed.setDescription(eventInformation.getDescription());

        return eventsInformationRepository.save(existed);
    }

    @Override
    public EventInformation getEventInformationByEventId(Long eventId) {

        return eventRepository.findById(eventId)
                .map(event -> event.getEventInformation())
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }

    @Override
    public EventInformation createEventInformation(Long eventId, EventInformation eventInformation) {
        return eventRepository.findById(eventId)
                .map(event -> {
                    EventInformation result = eventsInformationRepository.save(eventInformation);
                    event.setEventInformation(result);
                    eventRepository.save(event);
                    return result;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }


}
