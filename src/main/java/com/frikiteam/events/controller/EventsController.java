package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.resource.EventResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class EventsController {
    @Autowired
    private EventService eventService;
    @Autowired
    ModelMapper mapper;

    @GetMapping("events/search")
    public List<EventResource> getEventByName(@RequestParam String name) {
        List<Event> events = eventService.getEventByName(name);
        return events.stream()
                .map(event -> mapper.map(event, EventResource.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/events")
    public List<EventResource> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return events.stream()
                .map((event -> mapper.map(event, EventResource.class)))
                .collect(Collectors.toList());
    }

    @GetMapping("/events/{id}")
    public EventResource getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return mapper.map(event, EventResource.class);
    }

}
