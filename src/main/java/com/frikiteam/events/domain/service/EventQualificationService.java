package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.EventQualification;
import com.frikiteam.events.resource.EventQualificationResource;
import org.springframework.http.ResponseEntity;

public interface EventQualificationService {
    EventQualification saveEventQualification(EventQualification eventQualification);

    EventQualification getEventQualificationById(Long id);

    ResponseEntity<?> deleteEventQualification(Long id);

}
