package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Organizer;
import com.frikiteam.events.domain.service.OrganizerService;
import com.frikiteam.events.resource.OrganizerResource;
import com.frikiteam.events.resource.SaveOrganizerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrganizersController {
    @Autowired
    private OrganizerService organizerService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Organizers", description = "Get All Organizers by Pages", tags = {"organizers"})
    @ApiResponse(
            responseCode = "200",
            description = "All Organizers returned",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/organizers")
    public Page<OrganizerResource> getAllOrganizers(Pageable pageable){
        Page<Organizer> organizers = organizerService.getAllOrganizers(pageable);
        List<OrganizerResource> resources = organizers.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get a Organizer by id", description = "Get a ortganizer given an id", tags = {"organizers"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a Organizer",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/organizers/{id}")
    public OrganizerResource getByIdOrganizer(@PathVariable Long id){
        return convertToResource(organizerService.getOrganizerById(id));
    }

    @Operation(summary = "Save a Organizer", description = "Save a organizer", tags = {"organizers"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the organizer saved",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/organizers")
    public OrganizerResource saveOrganizer(@Valid @RequestBody SaveOrganizerResource resource) {
        Organizer organizer = convertToEntity(resource);
        return convertToResource(organizerService.saveOrganizer(organizer));
    }

    @Operation(summary = "Update a Organizer", description = "Update a organizer given an id and organizer body", tags = {"organizers"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the organizer updated",
            content = @Content(mediaType = "application/json")
    )
    @PutMapping("/organizers/{id}")
    public OrganizerResource updateCustomer(@PathVariable Long id, @RequestBody SaveOrganizerResource resource){
        Organizer organizer = convertToEntity(resource);
        return convertToResource(organizerService.updateOrganizer(id, organizer));
    }

    @Operation(summary = "Delete a Organizer", description = "remove a organizer given an id", tags = {"organizers"})
    @ApiResponse(
            responseCode = "200",
            description = "Return ResponseEntity",
            content = @Content(mediaType = "application/json")
    )
    @DeleteMapping("/organizers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        return organizerService.deleteOrganizer(id);
    }

    private Organizer convertToEntity(SaveOrganizerResource resource) {
        return mapper.map(resource, Organizer.class);
    }
    private OrganizerResource convertToResource(Organizer entity) {
        return mapper.map(entity, OrganizerResource.class);
    }
}
