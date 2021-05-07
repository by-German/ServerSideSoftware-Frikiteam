package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Organizer;
import com.frikiteam.events.domain.repositories.OrganizerRepository;
import com.frikiteam.events.domain.service.OrganizerService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrganizerServiceImpl implements OrganizerService {
    @Autowired
    private OrganizerRepository repository;

    @Override
    public Page<Organizer> getAllOrganizers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Organizer getOrganizerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organizer", "id", id));
    }

    @Override
    public Organizer saveOrganizer(Organizer customer) {
        return repository.save(customer);
    }

    @Override
    public ResponseEntity<?> deleteOrganizer(Long id) {
        Organizer organizer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organizer", "id", id));
        repository.delete(organizer);
        return ResponseEntity.ok().build();
    }

    @Override
    public Organizer updateOrganizer(Long id, Organizer organizer) {
        Organizer existed = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organizer", "id", id));

        existed.setFirstName(organizer.getFirstName());
        existed.setLastName(organizer.getLastName());
        existed.setEmail(organizer.getEmail());
        existed.setPassword(organizer.getPassword());
        existed.setDescription(organizer.getDescription());
        existed.setLogo(organizer.getLogo());
        existed.setVerified(organizer.getVerified());

        return repository.save(existed);
    }
}
