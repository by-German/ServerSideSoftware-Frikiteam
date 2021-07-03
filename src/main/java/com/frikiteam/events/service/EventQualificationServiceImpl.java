package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.EventQualification;
import com.frikiteam.events.domain.repositories.EventQualificationRepository;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.service.EventQualificationService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventQualificationServiceImpl implements EventQualificationService {

    @Autowired
    private EventQualificationRepository eventQualificationRepository;
    @Autowired
    private EventRepository eventRepository;

    @Override
    public EventQualification saveEventQualification(Long eventId, EventQualification eventQualification) {
        return eventRepository.findById(eventId).map(event -> {
            eventQualification.setEvent(event);
            return eventQualificationRepository.save(eventQualification);
        }).orElse(null);
    }

    @Override
    public ResponseEntity<?> deleteEventQualification(Long id) {
        EventQualification eventQualification = eventQualificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "EventQualification", "Id", id
                ));

        eventQualificationRepository.delete(eventQualification);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<EventQualification> getEventQualificationByEventId(Long eventId) {
        return eventRepository.findById(eventId).map(Event::getEventQualifications)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }

}
