package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.SocialNetwork;
import com.frikiteam.events.domain.model.Tag;
import com.frikiteam.events.domain.service.SocialNetworkService;
import com.frikiteam.events.domain.service.TagService;
import com.frikiteam.events.resource.SaveSocialNetworkResource;
import com.frikiteam.events.resource.SaveTagResource;
import com.frikiteam.events.resource.SocialNetworkResource;
import com.frikiteam.events.resource.TagResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social-networks")
public class SocialNetworksController {
    @Autowired
    private SocialNetworkService socialNetworkService;
    @Autowired
    private ModelMapper mapper;


    @PostMapping
    @Operation(summary = "Create Social network", tags = {"social-networks"})
    public SocialNetworkResource createTag(@RequestBody SaveSocialNetworkResource resource) {
        SocialNetwork socialNetwork = mapper.map(resource, SocialNetwork.class);
        return mapper.map(socialNetworkService.createSocialNetwork(socialNetwork), SocialNetworkResource.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Social Network by Id", tags = {"social-networks"})
    public ResponseEntity<?> deleteSocialNetwork(@PathVariable Long id){
        return socialNetworkService.deleteSocialNetwork(id);
    }
}
