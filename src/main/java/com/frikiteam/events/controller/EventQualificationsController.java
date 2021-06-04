package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.EventQualification;
import com.frikiteam.events.domain.service.EventQualificationService;
import com.frikiteam.events.resource.EventQualificationResource;
import com.frikiteam.events.resource.SaveEventQualificationResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EventQualificationsController {

    @Autowired
    ModelMapper mapper;

    @Autowired
    EventQualificationService _eventQualificationService;

    @PostMapping("/event_qualifications")
    @Operation(summary = "create qualification for an event", tags = {"events-qualification"})
    public EventQualificationResource saveEventQualification(@RequestBody SaveEventQualificationResource resource){
        mapper.getConfiguration().setAmbiguityIgnored(true);
        EventQualification eventQualification = convertToEntity(resource);
        return convertToResource(_eventQualificationService.saveEventQualification(eventQualification));
    }

    @GetMapping("/event_qualifications/{id}")
    @Operation(summary = "get qualification of a event by id", tags = {"events-qualification"})
    public EventQualificationResource getEventQualificationById(@PathVariable Long id){
        return convertToResource(_eventQualificationService.getEventQualificationById(id));
    }

    @DeleteMapping("/event_qualifications/{id}")
    @Operation(summary = "delete a qualification of an event", tags = {"events-qualification"})
    public ResponseEntity<?> deleteEventQualification(@PathVariable Long id){
        return _eventQualificationService.deleteEventQualification(id);
    }


    private EventQualification convertToEntity(SaveEventQualificationResource resource) {
        return mapper.map(resource, EventQualification.class);
    }
    private EventQualificationResource convertToResource(EventQualification entity) {
        return mapper.map(entity, EventQualificationResource.class);
    }
}
