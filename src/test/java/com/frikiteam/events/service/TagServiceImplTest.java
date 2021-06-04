package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.Tag;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.repositories.TagRepository;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.domain.service.TagService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TagServiceImplTest {
    @MockBean
    private TagRepository _tagRepository;

    @MockBean
    private EventRepository _eventRepository;

    @Autowired
    private TagService _tagService;

    @TestConfiguration
    static class TagServiceTestConfiguration {
        @Bean
        public TagService tagService() {
            return new TagServiceImpl();
        }
    }

    @Test
    public void whenSaveTagWithValidTagIdReturnsTag() {
        // Arrange
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName("Otakus");
        //tag.setDescription("A little description");
        when(_tagRepository.save(tag)).thenReturn(tag);

        // Act
        Tag result = _tagService.createTag(tag);

        // Assert
        assertThat(result).isEqualTo(tag);
    }

    @Test
    public void whenDeleteTagByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        long id = 1;
        String template = "Resource %s not found for %s with value %s";
        when(_tagRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Tag", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            ResponseEntity<?> result = _tagService.deleteTag(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

    @Test
    public void whenDeleteTagByIdWithValidIdThenReturnsResponseEntityOk() {
        // Arrange
        long id = 1;
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName("Papus");
        when(_tagRepository.findById(id)).thenReturn(Optional.of(tag));

        // Act
        ResponseEntity<?> result = _tagService.deleteTag(id);


        // Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());

    }

    @Test
    public void whenGetAllTagsByEventIdWithValidIdThenReturnsPageTags() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        List<Tag> tags = new ArrayList<>();
        event.setId(eventId);
        Tag tag = new Tag();
        tag.setId(1L);
        tags.add(tag);
        event.setTags(tags);

        when(_eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(_tagRepository.findAll()).thenReturn(tags);

        // Act
        Page<Tag> results = _tagService.getAllTagsByEventId(eventId, Pageable.unpaged());

        // Assert
        assertThat(results.getContent()).isEqualTo(tags);

    }

    @Test
    public void whenGetAllTagsByEventIdWithInvalidIdThenReturnsResourceNotFoundException() {
        long eventId = 1;
        Event event = new Event();
        List<Tag> tags = new ArrayList<>();
        event.setId(eventId);
        Tag tag = new Tag();
        tag.setId(1L);
        tags.add(tag);

        when(_eventRepository.findById(eventId)).thenReturn(Optional.empty());
        when(_tagRepository.findAll()).thenReturn(tags);

        // Act
        Throwable exception = catchThrowable(() -> {
            Page<Tag> results = _tagService.getAllTagsByEventId(eventId, Pageable.unpaged());
        });

        // Assert
        assertThat(exception)
        .isInstanceOf(ResourceNotFoundException.class)
        .hasMessage("Resource Event not found for Id with value 1");
    }
}
