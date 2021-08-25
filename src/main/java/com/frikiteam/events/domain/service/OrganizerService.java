package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.Organizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface OrganizerService {
    Page<Organizer> getAllOrganizers(Pageable pageable);
    Organizer getOrganizerById(Long id);
    Organizer saveOrganizer(Organizer organizer);
    ResponseEntity<?> deleteOrganizer(Long id);
    Organizer updateOrganizer(Long id, Organizer organizer);

    Page<Organizer> getAllByCustomerId(Long customerId, Pageable pageable);
    Organizer assignCustomer(Long customerId, Long organizerId);
    Organizer unnassignCustomer(Long customerId, Long organizerId);
}
