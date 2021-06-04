package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.SocialNetwork;
import com.frikiteam.events.domain.model.Tag;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.repositories.SocialNetworkRepository;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.domain.service.SocialNetworkService;
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

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SocialNetworkServiceImplTest {

    @MockBean
    private SocialNetworkRepository socialNetworkRepository;

    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private SocialNetworkService socialNetworkService;

    @TestConfiguration
    static class SocialNetworkServiceTestConfiguration {
        @Bean
        public SocialNetworkService socialNetworkService() { return new SocialNetworkServiceImpl(); }
    }

    @Test
    public void whenGetAllSocialNetworksByEventIdWithValidIdThenReturnsListSocialNetworks() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        List<SocialNetwork> socialNetworks = new ArrayList<>();
        event.setId(eventId);
        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.setId(1L);
        socialNetworks.add(socialNetwork);
        event.setSocialNetworks(socialNetworks);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(socialNetworkRepository.findAll()).thenReturn(socialNetworks);

        // Act
        List<SocialNetwork> results = socialNetworkService.getAllSocialNetworksByEventId(eventId);

        // Assert
        assertThat(results).isEqualTo(socialNetworks);

    }

    @Test
    public void whenGetAllSocialNetworksByEventIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        List<SocialNetwork> socialNetworks = new ArrayList<>();
        event.setId(eventId);
        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.setId(1L);
        socialNetworks.add(socialNetwork);

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        when(socialNetworkRepository.findAll()).thenReturn(socialNetworks);

        // Act
        Throwable exception = catchThrowable(() -> {
            List<SocialNetwork> results = socialNetworkService.getAllSocialNetworksByEventId(eventId);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Event not found for Id with value 1");

    }

    @Test
    public void whenSaveSocialNetworkWithValidIdThenReturnsSocialNetwork() {
        // Arrange
        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.setId(1L);
        socialNetwork.setName("name");
        when(socialNetworkRepository.save(socialNetwork)).thenReturn(socialNetwork);

        // Act
        SocialNetwork result = socialNetworkService.createSocialNetwork(socialNetwork);

        // Assert
        assertThat(result).isEqualTo(socialNetwork);

    }

    @Test
    public void whenDeleteSocialNetworkByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        long id = 1;
        String template = "Resource %s not found for %s with value %s";
        when(socialNetworkRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "SocialNetwork", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            ResponseEntity<?> result = socialNetworkService.deleteSocialNetwork(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

    @Test
    public void whenDeleteSocialNetworkByIdWithValidIdThenReturnsResponseEntityOk() {
        // Arrange
        long id = 1;
        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.setId(id);
        socialNetwork.setName("Any name");
        when(socialNetworkRepository.findById(id)).thenReturn(Optional.of(socialNetwork));

        // Act
        ResponseEntity<?> result = socialNetworkService.deleteSocialNetwork(id);

        // Assert
        assertThat(result).isEqualTo(ResponseEntity.ok().build());

    }

    @Test
    public void whenUnassignEventSocialNetworkWithIdsValidThenReturnsSocialNetwork() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        long socialNetworkId = 1;
        SocialNetwork socialNetwork = new SocialNetwork();

        when(socialNetworkRepository.findById(socialNetworkId)).thenReturn(Optional.of(socialNetwork));
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(socialNetworkRepository.save(socialNetwork)).thenReturn(socialNetwork);

        // Act
        SocialNetwork result = socialNetworkService.unassignEventSocialNetwork(eventId, socialNetworkId);

        // Assert
        assertThat(result).isEqualTo(socialNetwork);

    }

    @Test
    public void whenAssignEventSocialNetworkWithIdsValidThenReturnsSocialNetwork() {
        // Arrange
        long eventId = 1;
        Event event = new Event();
        long socialNetworkId = 1;
        SocialNetwork socialNetwork = new SocialNetwork();

        when(socialNetworkRepository.findById(socialNetworkId)).thenReturn(Optional.of(socialNetwork));
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(socialNetworkRepository.save(socialNetwork)).thenReturn(socialNetwork);

        // Act
        SocialNetwork result = socialNetworkService.assignEventSocialNetwork(eventId, socialNetworkId);

        // Assert
        assertThat(result).isEqualTo(socialNetwork);
    }

    @Test
    public void whenAssignEventSocialNetworkWithEventIdInvalidThenReturnsResourceNotFoundException(){
        // Arrange
        long eventId = 1;
        Event event = new Event();
        long socialId = 1;
        SocialNetwork socialNetwork = new SocialNetwork();

        when(socialNetworkRepository.findById(socialId)).thenReturn(Optional.of(socialNetwork));
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        when(socialNetworkRepository.save(socialNetwork)).thenReturn(socialNetwork);

        // Act
        Throwable exception = catchThrowable(() -> {
            SocialNetwork result = socialNetworkService.assignEventSocialNetwork(eventId, socialId);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Event not found for Id with value 1");

    }

}
