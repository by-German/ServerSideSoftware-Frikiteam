package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Comment;
import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.model.User;
import com.frikiteam.events.domain.repositories.CommentRepository;
import com.frikiteam.events.domain.repositories.CustomerRepository;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.repositories.UserRepository;
import com.frikiteam.events.domain.service.CommentService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Comment> getAllCommentsByEventId(Long eventId, Pageable pageable) {
        return eventRepository.findById(eventId)
                .map(event -> {
                    List<Comment> comments = commentRepository.findAll()
                            .stream()
                            .filter(comment -> comment.getEvent() == event)
                            .collect(Collectors.toList());
                    return new PageImpl<>(comments, pageable, comments.size());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }

    @Override
    public Comment createComment(Long eventId, Long userId, Comment comment) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        return eventRepository.findById(eventId)
                .map(event -> {
                    comment.setEvent(event);
                    comment.setUser(user);
                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }


}
