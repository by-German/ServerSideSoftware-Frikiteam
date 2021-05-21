package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.EventType;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.repositories.EventTypeRepository;
import com.frikiteam.events.domain.service.EventTypeService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventTypeServiceImpl implements EventTypeService {
    @Autowired
    private EventTypeRepository eventTypeRepository;
    @Autowired
    private EventRepository eventRepository;


    @Override
    public Page<EventType> getAllEventTypes(Pageable pageable) {
        return eventTypeRepository.findAll(pageable);
    }


    @Override
    public EventType getEventTypeById(Long eventTypeId) {
        return eventTypeRepository.findById(eventTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("EventType", "Id", eventTypeId));
    }

    @Override
    public EventType createEventType(EventType eventType) {
        return eventTypeRepository.save(eventType);
    }

    @Override
    public EventType updateEventType(Long eventTypeId, EventType eventTypeDetails) {
        return eventTypeRepository.findById(eventTypeId)
                .map(eventType -> {
                    eventType.setName(eventTypeDetails.getName());
                    return eventTypeRepository.save(eventType); })
                .orElseThrow(() -> new ResourceNotFoundException("EventType", "Id", eventTypeId));
    }

    @Override
    public ResponseEntity<?> deleteEventType(Long eventTypeId) {
        return eventTypeRepository.findById(eventTypeId)
                .map(EventType -> {
                    eventTypeRepository.delete(EventType);
                    return ResponseEntity.ok().build(); })
                .orElseThrow(() -> new ResourceNotFoundException("EventType", "Id", eventTypeId));

    }

    @Override
    public EventType assignTypeEventToEvent(Long typeId, Long eventId) {
        return eventRepository.findById(eventId)
                .map(event -> {
                    EventType eventType = eventTypeRepository.findById(typeId)
                            .orElseThrow(() -> new ResourceNotFoundException("EventType", "Id", typeId));
                    event.setEventType(eventType);
                    eventRepository.save(event);
                    return eventType;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }
}
