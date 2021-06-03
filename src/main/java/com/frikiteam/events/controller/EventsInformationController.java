package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.EventInformation;
import com.frikiteam.events.domain.service.EventInformationService;
import com.frikiteam.events.resource.EventInformationResource;
import com.frikiteam.events.resource.SaveEventInformationResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events/{eventId}/information")
public class EventsInformationController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EventInformationService eventInformationService;

    @GetMapping
    @Operation(summary = "get information of a event by event id", tags = {"events-information"})
    public EventInformationResource getEventInformationByEventId(@PathVariable Long eventId) {
        return mapper.map
                (eventInformationService.getEventInformationByEventId(eventId), EventInformationResource.class);
    }

    @PostMapping
    @Operation(summary = "create information for an event", tags = {"events-information"})
    public EventInformationResource createEventInformation
            (@PathVariable Long eventId, @RequestBody SaveEventInformationResource resource){

        EventInformation eventInformation = mapper.map(resource, EventInformation.class);
        return mapper.map(eventInformationService
                .createEventInformation(eventId, eventInformation), EventInformationResource.class);
    }
}
