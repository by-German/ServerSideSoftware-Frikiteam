package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Page<Comment> getAllCommentsByEventId(Long eventId, Pageable pageable);

    Comment createComment(Long eventId, Long userId, Comment comment);
}
