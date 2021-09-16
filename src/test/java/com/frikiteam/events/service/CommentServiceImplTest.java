package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Comment;
import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.repositories.CommentRepository;
import com.frikiteam.events.domain.repositories.CustomerRepository;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.repositories.UserRepository;
import com.frikiteam.events.domain.service.CommentService;
import com.frikiteam.events.domain.service.CustomerService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CommentServiceImplTest {
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private EventRepository eventRepository;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CommentService commentService;

    @TestConfiguration
    static class CommentServiceTestConfiguration {
        @Bean
        public CommentService commentService() {
            return new CommentServiceImpl();
        }
    }

    @Test
    public void whenGetAllCommentsByEventIdWithValidIdThenReturnsPageComments() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        event.setId(eventId);
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setEvent(event);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);


        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(commentRepository.findAll()).thenReturn(comments);

        // Act
        Page<Comment> results = commentService.getAllCommentsByEventId(eventId, Pageable.unpaged());

        // Assert
        assertThat(results.getContent()).isEqualTo(comments);
    }

    @Test
    public void whenGetAllCommentsByEventIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        event.setId(eventId);
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setEvent(event);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);


        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        when(commentRepository.findAll()).thenReturn(comments);

        // Act
        Throwable exception = catchThrowable(() -> {
            Page<Comment> results = commentService.getAllCommentsByEventId(eventId, Pageable.unpaged());
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Event not found for Id with value 1");
    }

    @Test
    public void whenCreateCommentWithIdsValidThenReturnsComment() {
        // Arrange
        long eventId = 1;
        long userId = 1;
        Event event = new Event();
        event.setId(eventId);
        Customer customer = new Customer();
        customer.setId(userId);
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setEvent(event);
        comment.setUser(customer);

        when(userRepository.findById(userId)).thenReturn(Optional.of(customer));
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(commentRepository.save(comment)).thenReturn(comment);

        // Act
        Comment result = commentService.createComment(eventId, userId, comment);

        // Assert
        assertThat(result).isEqualTo(comment);
    }

    @Test
    public void whenCreateCommentWithEventIdInvalidThenReturnsResourceNotFoundException() {
        // Arrange
        long eventId = 1;
        long userId = 1;
        Event event = new Event();
        event.setId(eventId);
        Customer customer = new Customer();
        customer.setId(userId);
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setEvent(event);
        comment.setUser(customer);

        when(userRepository.findById(userId)).thenReturn(Optional.of(customer));
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        when(commentRepository.save(comment)).thenReturn(comment);

        // Act
        Throwable exception = catchThrowable(() -> {
            Comment result = commentService.createComment(eventId, userId, comment);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Event not found for Id with value 1");
    }

    @Test
    public void whenCreateCommentWithCustomerIdInvalidThenReturnsResourceNotFoundException() {
        // Arrange
        long eventId = 1;
        long userId = 1;
        Event event = new Event();
        event.setId(eventId);
        Customer customer = new Customer();
        customer.setId(userId);
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setEvent(event);
        comment.setUser(customer);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(commentRepository.save(comment)).thenReturn(comment);

        // Act
        Throwable exception = catchThrowable(() -> {
            Comment result = commentService.createComment(eventId, userId, comment);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource User not found for Id with value 1");
    }
}