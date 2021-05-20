package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.resource.EventResource;
import com.frikiteam.events.resource.SaveEventResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizers/{organizerId}/events")
public class OrganizerEventsController {
    @Autowired
    private EventService eventService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/places/{placeId}")
    public EventResource createEvent
            (@PathVariable Long organizerId, @PathVariable Long placeId,@RequestBody SaveEventResource resource) {

        // creation event
        Event event = mapper.map(resource, Event.class);
        return mapper.map(
                eventService.createEvent(organizerId, placeId, event), EventResource.class);
    }
}
