package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.EventType;
import com.frikiteam.events.domain.service.EventTypeService;
import com.frikiteam.events.resource.EventResource;
import com.frikiteam.events.resource.SaveEventTypeResource;
import com.frikiteam.events.resource.EventTypeResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

// Decrecated
@RestController
@RequestMapping("/api")
public class EventTypesController {
    @Autowired
    private EventTypeService eventTypeService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get All Event Types", description = "Get All Event Types by pages", tags = {"event types"})
    @ApiResponse(
            responseCode = "200",
            description = "All Event Types returned",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/event-types")
    public Page<EventTypeResource> getAllEventTypes(Pageable pageable) {
        List<EventTypeResource> eventTypes = eventTypeService.getAllEventTypes(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int typeEventsCount = eventTypes.size();
        return new PageImpl<>(eventTypes, pageable, typeEventsCount);
    }

    @Operation(summary = "Get Event Type by Id", description = "Get Event Type by Id", tags = {"event types"})
    @ApiResponse(
            responseCode = "200",
            description = "Get Event Type by Id",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/event-types/{id}")
    public EventTypeResource getEventTypeById(@PathVariable Long id) {
        return convertToResource(eventTypeService.getEventTypeById(id));
    }

    @Operation(summary = "Create an Event Type", description = "Create an Event Type", tags = {"event types"})
    @ApiResponse(
            responseCode = "200",
            description = "Create an Event Type",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/type-events")
    public EventTypeResource createEventType(@Valid @RequestBody SaveEventTypeResource resource) {
        return convertToResource(eventTypeService.createEventType(convertToEntity(resource)));
    }

    @Operation(summary = "Update an Event Type", description = "Update an Event Type", tags = {"event types"})
    @ApiResponse(
            responseCode = "200",
            description = "Update an Event Type",
            content = @Content(mediaType = "application/json")
    )
    @PutMapping("/type-events/{id}")
    public EventTypeResource updateEventType(@PathVariable Long id, @Valid @RequestBody SaveEventTypeResource resource) {
        return convertToResource(eventTypeService.updateEventType(id, convertToEntity(resource)));
    }

    @PutMapping("/type-events/{typeId}/events/{eventId}")
    @Operation(summary = "assign type event to event", tags = {"event types"})
    public EventTypeResource assignTypeEventToEvent(@PathVariable Long typeId, @PathVariable Long eventId) {
        return convertToResource(eventTypeService.assignTypeEventToEvent(typeId, eventId));
    }

    private EventType convertToEntity(SaveEventTypeResource resource) {
        return mapper.map(resource, EventType.class);
    }
    private EventTypeResource convertToResource(EventType entity) {
        return mapper.map(entity, EventTypeResource.class);
    }



}
