package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Tag;
import com.frikiteam.events.domain.service.TagService;
import com.frikiteam.events.resource.SaveTagResource;
import com.frikiteam.events.resource.TagResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a tag by Id", tags = {"tags"})
    public ResponseEntity<?> deleteTag(@PathVariable Long id){
        return tagService.deleteTag(id);
    }

    @GetMapping
    @Operation(summary = "Get a list of All tags", tags = {"tags"})
    public List<TagResource> getAllTags() {
        return  tagService.getAllTags()
                .stream()
                .map(tag -> mapper.map(tag, TagResource.class ))
                .collect(Collectors.toList());
    }

}
