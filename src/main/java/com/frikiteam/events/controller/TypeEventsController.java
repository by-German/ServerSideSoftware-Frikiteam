package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.TypeEvent;
import com.frikiteam.events.domain.service.TypeEventService;
import com.frikiteam.events.resource.SaveTypeEventResource;
import com.frikiteam.events.resource.TypeEventResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TypeEventsController {
    @Autowired
    private TypeEventService typeEventService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/type-events")
    public Page<TypeEventResource> getAllTypeEvents(Pageable pageable) {
        List<TypeEventResource> typeEvents = typeEventService.getAllTypeEvents(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int typeEventsCount = typeEvents.size();
        return new PageImpl<>(typeEvents, pageable, typeEventsCount);
    }


    @GetMapping("/type-events/{id}")
    public TypeEventResource getTypeEventById(@PathVariable Long id) {
        return convertToResource(typeEventService.getTypeEventById(id));
    }

    @PostMapping("/type-events")
    public TypeEventResource createTypeEvent(@Valid @RequestBody SaveTypeEventResource resource) {
        return convertToResource(typeEventService.createTypeEvent(convertToEntity(resource)));
    }

    @PutMapping("/type-events/{id}")
    public TypeEventResource updateTypeEvent(@PathVariable Long id, @Valid @RequestBody SaveTypeEventResource resource) {
        return convertToResource(typeEventService.updateTypeEvent(id, convertToEntity(resource)));
    }

    private TypeEvent convertToEntity(SaveTypeEventResource resource) {
        return mapper.map(resource, TypeEvent.class);
    }

    private TypeEventResource convertToResource(TypeEvent entity) {
        return mapper.map(entity, TypeEventResource.class);
    }



}
