package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.EventQualification;
import com.frikiteam.events.domain.repositories.EventQualificationRepository;
import com.frikiteam.events.domain.service.EventQualificationService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventQualificationServiceImpl implements EventQualificationService {

    @Autowired
    private EventQualificationRepository eventQualificationRepository;

    @Override
    public EventQualification saveEventInformation(EventQualification eventQualification) {
        return eventQualificationRepository.save(eventQualification);
    }

    @Override
    public EventQualification getEventInformationById(Long id) {
        return eventQualificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "EventQualification", "Id", id
                ));
    }

    @Override
    public ResponseEntity<?> deleteEventInformation(Long id) {
        EventQualification eventQualification = eventQualificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "EventQualification", "Id", id
                ));

        eventQualificationRepository.delete(eventQualification);
        return ResponseEntity.ok().build();
    }
}
