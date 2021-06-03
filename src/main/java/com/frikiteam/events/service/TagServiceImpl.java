package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.model.Tag;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.repositories.TagRepository;
import com.frikiteam.events.domain.service.TagService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private EventRepository eventRepository;


    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Page<Tag> getAllTagsByEventId(Long eventId, Pageable pageable) {
        return eventRepository.findById(eventId)
                .map(event -> {
                    List<Tag> tags = event.getTags();
                    return new PageImpl<>(tags, pageable, tags.size());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }

    @Override
    public ResponseEntity<?> deleteTag(Long id) {
        return tagRepository.findById(id)
                .map(tag -> {
                    tagRepository.delete(tag);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", id));
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
