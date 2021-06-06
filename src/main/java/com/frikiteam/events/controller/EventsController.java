package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.resource.EventResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/events")
public class EventsController {
    @Autowired
    private EventService eventService;
    @Autowired
    ModelMapper mapper;

    @GetMapping("/search")
    public EventResource getEventByName(@RequestParam String name) {
        Event event = eventService.getEventByName(name);
        return mapper.map(event, EventResource.class);
    }

}
