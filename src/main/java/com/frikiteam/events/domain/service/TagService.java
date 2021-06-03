package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagService{
    Tag createTag(Tag tag);

    Page<Tag> getAllTagsByEventId(Long eventId, Pageable pageable);

    ResponseEntity<?> deleteTag(Long id);

    List<Tag> getAllTags();
}
