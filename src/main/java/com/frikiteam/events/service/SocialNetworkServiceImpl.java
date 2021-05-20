package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.SocialNetwork;
import com.frikiteam.events.domain.repositories.EventRepository;
import com.frikiteam.events.domain.repositories.SocialNetworkRepository;
import com.frikiteam.events.domain.service.SocialNetworkService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import com.frikiteam.events.resource.SaveSocialNetworkResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialNetworkServiceImpl implements SocialNetworkService {
    @Autowired
    private SocialNetworkRepository socialNetworkRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<SocialNetwork> getAllSocialNetworksByEventId(Long eventId) {
        return eventRepository.findById(eventId)
                .map(event -> event.getSocialNetworks())
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }

    @Override
    public SocialNetwork createSocialNetwork(SocialNetwork socialNetwork) {
        return socialNetworkRepository.save(socialNetwork);
    }

    @Override
    public ResponseEntity<?> deleteSocialNetwork(Long id) {
        return socialNetworkRepository.findById(id)
                .map(socialNetwork -> {
                    socialNetworkRepository.delete(socialNetwork);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("SocialNetwork", "Id", id));
    }

    @Override
    public SocialNetwork assignEventSocialNetwork(Long eventId, Long socialId) {
        return socialNetworkRepository.findById(socialId)
                .map(socialNetwork -> {
                    Event event = eventRepository.findById(eventId)
                            .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
                    event.getSocialNetworks().add(socialNetwork);
                    eventRepository.save(event);
                    return socialNetwork;
                })
                .orElseThrow(() -> new ResourceNotFoundException("SocialNetwork", "Id", socialId));
    }

    @Override
    public SocialNetwork unassignEventSocialNetwork(Long eventId, Long socialId) {
        return socialNetworkRepository.findById(socialId)
                .map(socialNetwork -> {
                    Event event = eventRepository.findById(eventId)
                            .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
                    event.getSocialNetworks().remove(socialNetwork);
                    eventRepository.save(event);
                    return socialNetwork;
                })
                .orElseThrow(() -> new ResourceNotFoundException("SocialNetwork", "Id", socialId));    }
}
