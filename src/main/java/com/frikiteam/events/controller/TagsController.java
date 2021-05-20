package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Tag;
import com.frikiteam.events.domain.service.TagService;
import com.frikiteam.events.resource.SaveTagResource;
import com.frikiteam.events.resource.TagResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagsController {
    @Autowired
    private TagService tagService;
    @Autowired
    private ModelMapper mapper;


    @PostMapping
    @Operation(summary = "Create tag", tags = {"tags"})
    public TagResource createTag(@RequestBody SaveTagResource resource) {
        Tag tag = mapper.map(resource, Tag.class);
        return mapper.map(tagService.createTag(tag), TagResource.class);
    }
}
