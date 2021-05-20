package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.SocialNetwork;
import com.frikiteam.events.resource.SaveSocialNetworkResource;
import com.frikiteam.events.resource.SocialNetworkResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SocialNetworkService {
    List<SocialNetwork> getAllSocialNetworksByEventId(Long eventId);

    SocialNetwork createSocialNetwork(SocialNetwork socialNetwork);

    ResponseEntity<?> deleteSocialNetwork(Long id);

    SocialNetwork assignEventSocialNetwork(Long eventId, Long socialId);

    SocialNetwork unassignEventSocialNetwork(Long eventId, Long socialId);
}
