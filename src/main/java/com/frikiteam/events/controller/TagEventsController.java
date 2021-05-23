package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.resource.EventResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tags/{tagId}/events")
public class TagEventsController {
    @Autowired
    private EventService eventService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    @Operation(summary = "Get all events by tagId", tags = {"tags-events"})
    public Page<EventResource> getAllEventsByTagId(@PathVariable Long tagId, Pageable pageable) {
        Page<Event> eventPage = eventService.getAllEventsByTagId(tagId, pageable);
        List<EventResource> resources = eventPage.getContent()
                .stream()
                .map(event -> modelMapper.map(event, EventResource.class))
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
}
