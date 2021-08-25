package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.model.Organizer;
import com.frikiteam.events.domain.repositories.CustomerRepository;
import com.frikiteam.events.domain.repositories.OrganizerRepository;
import com.frikiteam.events.domain.service.OrganizerService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerServiceImpl implements OrganizerService {
    @Autowired
    private OrganizerRepository repository;
    @Autowired
    private CustomerRepository customerRepository;

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

    @Override
    public Page<Organizer> getAllByCustomerId(Long customerId, Pageable pageable) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    List<Organizer> organizers = customer.getOrganizers();
                    return new PageImpl<>(organizers, pageable, organizers.size());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));
    }

    @Override
    public Organizer assignCustomer(Long customerId, Long organizerId) {
        return repository.findById(organizerId)
                .map(organizer -> {
                    Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));
                    customer.getOrganizers().add(organizer);
                    customerRepository.save(customer);
                    return organizer;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Organizer", "Id", organizerId));
    }

    @Override
    public Organizer unnassignCustomer(Long customerId, Long organizerId) {
        return repository.findById(organizerId)
                .map(organizer -> {
                    Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));
                    customer.getOrganizers().remove(organizer);
                    customerRepository.save(customer);
                    return organizer;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Organizer", "Id", organizerId));
    }
}
