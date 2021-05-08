package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.EventType;
import com.frikiteam.events.domain.service.EventTypeService;
import com.frikiteam.events.resource.SaveEventTypeResource;
import com.frikiteam.events.resource.EventTypeResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EventTypesController {
    @Autowired
    private EventTypeService eventTypeService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/event-types")
    public Page<EventTypeResource> getAllTypeEvents(Pageable pageable) {
        List<EventTypeResource> eventTypes = eventTypeService.getAllEventTypes(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int typeEventsCount = eventTypes.size();
        return new PageImpl<>(eventTypes, pageable, typeEventsCount);
    }


    @GetMapping("/event-types/{id}")
    public EventTypeResource getTypeEventById(@PathVariable Long id) {
        return convertToResource(eventTypeService.getEventTypeById(id));
    }

    @PostMapping("/type-events")
    public EventTypeResource createTypeEvent(@Valid @RequestBody SaveEventTypeResource resource) {
        return convertToResource(eventTypeService.createEventType(convertToEntity(resource)));
    }

    @PutMapping("/type-events/{id}")
    public EventTypeResource updateTypeEvent(@PathVariable Long id, @Valid @RequestBody SaveEventTypeResource resource) {
        return convertToResource(eventTypeService.updateEventType(id, convertToEntity(resource)));
    }

    private EventType convertToEntity(SaveEventTypeResource resource) {
        return mapper.map(resource, EventType.class);
    }

    private EventTypeResource convertToResource(EventType entity) {
        return mapper.map(entity, EventTypeResource.class);
    }



}
