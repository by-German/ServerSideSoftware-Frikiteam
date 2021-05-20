package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.Tag;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.domain.service.TagService;
import com.frikiteam.events.resource.EventResource;
import com.frikiteam.events.resource.TagResource;
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
@RequestMapping("/api/event/{eventId}")
public class EventTagsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EventService eventService;
    @Autowired
    private TagService tagService; // TODO: to extract

    @Operation(summary = "Assign event to tag", tags = {"events-tags"})
    @PostMapping("tags/{tagId}")
    public EventResource assignEventTag(@PathVariable Long eventId, @PathVariable Long tagId) {
        return mapper.map(eventService.assignEventTag(eventId, tagId), EventResource.class);
    }

    @Operation(summary = "Unassign event to tag", tags = {"events-tags"})
    @DeleteMapping("tags/{tagId}")
    public EventResource unassignEventTag(@PathVariable Long eventId, @PathVariable Long tagId) {
        return mapper.map(eventService.unassignEventTag(eventId, tagId), EventResource.class);
    }

    @Operation(summary = "Get all tags by eventId", tags = {"events-tags"})
    @GetMapping("tags")
    public Page<TagResource> getAllTagsByEventId(@PathVariable Long eventId, Pageable pageable) {
        Page<Tag> tagsPage = tagService.getAllTagsByEventId(eventId, pageable);
        List<TagResource> resources = tagsPage.getContent()
                .stream()
                .map(tag -> mapper.map(tag, TagResource.class))
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

}
