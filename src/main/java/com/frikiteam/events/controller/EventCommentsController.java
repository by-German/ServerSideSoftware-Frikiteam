package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Comment;
import com.frikiteam.events.domain.service.CommentService;
import com.frikiteam.events.resource.CommentResource;
import com.frikiteam.events.resource.SaveCommentResource;
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
@RequestMapping("/api/events/{eventId}")
public class EventCommentsController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/comments")
    @Operation(summary = "Get all Comments by Event Id, in pages", tags = {"events-comments"})
    public Page<CommentResource> getAllCommentsByEventId(@PathVariable Long eventId, Pageable pageable) {
        Page<Comment> commentPage = commentService.getAllCommentsByEventId(eventId, pageable);
        List<CommentResource> resources = commentPage.getContent()
                .stream()
                .map(comment -> mapper.map(comment, CommentResource.class))
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("users/{userId}/comments")
    @Operation(summary = "Create a comment for a event", tags = "events-comments")
    public CommentResource createComment
            (@PathVariable Long eventId, @PathVariable Long userId, SaveCommentResource resource) {

        Comment comment = mapper.map(resource, Comment.class);
        return mapper.map(commentService.createComment(eventId, userId, comment), CommentResource.class);
    }


}
