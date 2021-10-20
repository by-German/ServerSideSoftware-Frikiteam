package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.domain.model.EventQualification;
import com.frikiteam.events.domain.service.EventInformationService;
import com.frikiteam.events.resource.EventInformationResource;
import com.frikiteam.events.resource.EventQualificationResource;
import com.frikiteam.events.resource.SaveEventInformationResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events/{eventId}/information")
public class EventsInformationController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EventInformationService eventInformationService;

    @GetMapping
    @Operation(summary = "get information of a event by event id", tags = {"events-information"})
    public List<EventInformationResource> getEventInformationByEventId(@PathVariable Long eventId) {
        List<EventInformation> informations = eventInformationService.getEventInformationByEventId(eventId);
        return informations.stream()
                .map(eventInformation -> mapper.map(eventInformation, EventInformationResource.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "create information for an event", tags = {"events-information"})
    public EventInformationResource createEventInformation
            (@PathVariable Long eventId, @RequestBody SaveEventInformationResource resource){

        EventInformation eventInformation = mapper.map(resource, EventInformation.class);
        return mapper.map(eventInformationService
                .createEventInformation(eventId, eventInformation), EventInformationResource.class);
    }

    @PutMapping("{id}")
    @Operation(summary = "update information for an event", tags = {"events-information"})
    public EventInformationResource updateEventInformation
            (@PathVariable Long eventId, @PathVariable Long id, @RequestBody SaveEventInformationResource resource){

        EventInformation eventInformation = mapper.map(resource, EventInformation.class);
        return mapper.map(eventInformationService
                .updateEventInformation(id, eventInformation), EventInformationResource.class);
    }
}
