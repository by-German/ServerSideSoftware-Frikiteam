package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.SocialNetwork;
import com.frikiteam.events.domain.repositories.SocialNetworkRepository;
import com.frikiteam.events.domain.service.SocialNetworkService;
import com.frikiteam.events.resource.SaveSocialNetworkResource;
import com.frikiteam.events.resource.SocialNetworkResource;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.catalina.LifecycleState;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events/{eventId}/social-networks")
public class EventSocialNetworksController {
    @Autowired
    private SocialNetworkService socialNetworkService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @Operation(summary = "Get all social networks by event id", tags = {"event-social-networks"})
    public List<SocialNetworkResource> getAllSocialNetworksByEventId(@PathVariable Long eventId) {
        List<SocialNetwork> socialNetworks = socialNetworkService.getAllSocialNetworksByEventId(eventId);
        return socialNetworks.stream()
                .map(socialNetwork -> mapper.map(socialNetwork, SocialNetworkResource.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/{socialId}")
    @Operation(summary = "Assign social network to event", tags = {"event-social-networks"})
    public SocialNetworkResource assignEventSocialNetwork(@PathVariable Long eventId, @PathVariable Long socialId) {
        return mapper.map(socialNetworkService.assignEventSocialNetwork(eventId, socialId), SocialNetworkResource.class);
    }

    @DeleteMapping("/{socialId}")
    @Operation(summary = "Unassign social network to event", tags = {"event-social-networks"})
    public SocialNetworkResource unassignEventSocialNetwork(@PathVariable Long eventId, @PathVariable Long socialId) {
        return mapper.map(socialNetworkService.unassignEventSocialNetwork(eventId, socialId), SocialNetworkResource.class);
    }
}
