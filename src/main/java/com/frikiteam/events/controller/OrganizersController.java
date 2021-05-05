package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Organizer;
import com.frikiteam.events.domain.service.OrganizerService;
import com.frikiteam.events.resource.OrganizerResource;
import com.frikiteam.events.resource.SaveOrganizerResource;
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

    @GetMapping("/organizers")
    public Page<OrganizerResource> getAllOrganizers(Pageable pageable){
        Page<Organizer> organizers = organizerService.getAllOrganizers(pageable);
        List<OrganizerResource> resources = organizers.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/organizers/{id}")
    public OrganizerResource getByIdOrganizer(@PathVariable Long id){
        return convertToResource(organizerService.getOrganizerById(id));
    }

    @PostMapping("/organizers")
    public OrganizerResource saveCustomer(@Valid @RequestBody SaveOrganizerResource resource) {
        Organizer organizer = convertToEntity(resource);
        return convertToResource(organizerService.saveOrganizer(organizer));
    }

    @PutMapping("/organizers/{id}")
    public OrganizerResource updateCustomer(@PathVariable Long id, @RequestBody SaveOrganizerResource resource){
        Organizer organizer = convertToEntity(resource);
        return convertToResource(organizerService.updateOrganizer(id, organizer));
    }

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
