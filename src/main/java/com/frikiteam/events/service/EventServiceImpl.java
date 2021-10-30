package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.model.Event;
import com.frikiteam.events.domain.model.Organizer;
import com.frikiteam.events.domain.model.Place;
import com.frikiteam.events.domain.repositories.*;
import com.frikiteam.events.domain.service.EventService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Event createEvent(Long organizerId, Event event) {
        Organizer organizer = organizerRepository.findById(organizerId)
                .orElseThrow(() -> new ResourceNotFoundException("Organizer", "id", organizerId));

        // assign organizer to event
        event.setSold(0);
        event.setOrganizer(organizer);

        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long organizerId, Long eventId, Event event) {
        return organizerRepository.findById(organizerId)
                .map(organizer -> {
                    Event event1 = eventRepository.findById(eventId)
                            .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
                    if (event1.getOrganizer() != organizer)
                        throw new RuntimeException("Event " + eventId + " for Organizer " + organizerId + " not found");
                    event1.setLogo(event.getLogo());
                    event1.setInformation(event.getInformation());
                    event1.setName(event.getName());
                    event1.setPrice(event.getPrice());
                    event1.setQuantity(event.getQuantity());
                    event1.setVerified(event.getVerified());
                    event1.setStartDate(event.getStartDate());
                    event1.setEndDate(event.getEndDate());
                    return eventRepository.save(event1);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Organizer", "Id", organizerId));
    }

    @Override
    public Event sellTicket(Long eventId, int quantity) {
        return eventRepository.findById(eventId).map(event -> {
            int sell = event.getSold() + quantity;
            if (sell <= event.getQuantity())
                event.setSold(event.getSold() + quantity);
            else throw new RuntimeException("You cannot exceed the number of tickets sold " +  sell + "/" +  quantity);
            return eventRepository.save(event);
        }).orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }

    @Override
    public Event assignEventTag(Long eventId, Long tagId) {
        return tagRepository.findById(tagId)
                .map(tag -> {
                    Event event = eventRepository.findById(eventId)
                            .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
                    event.getTags().add(tag);
                    return eventRepository.save(event);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
    }

    @Override
    public Page<Event> getAllEventsByTagId(Long tagId, Pageable pageable) {
        return tagRepository.findById(tagId)
                .map(tag -> {
                    List<Event> events = tag.getEvents();
                    return new PageImpl<>(events, pageable, events.size());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
    }

    @Override
    public Page<Event> getAllEventsByOrganizerId(Long organizerId, Pageable pageable) {
        return organizerRepository.findById(organizerId)
                .map(organizer -> {
                    List<Event> events = organizer.getEvents();
                    return new PageImpl<>(events, pageable, events.size());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", organizerId));
    }

    @Override
    public Event unassignEventTag(Long eventId, Long tagId) {
        return tagRepository.findById(tagId)
                .map(tag -> {
                    Event event = eventRepository.findById(eventId)
                            .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
                    event.getTags().remove(tag);
                    return eventRepository.save(event);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));    }

    @Override
    public List<Event> getEventByName(String name) {
        return eventRepository.findByNameContaining(name);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));    }

    @Override
    public Page<Event> getAllEventsByCustomerId(Long customerId, Pageable pageable) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    List<Event> events = customer.getEvents();
                    return new PageImpl<>(events, pageable, events.size());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));
    }

    @Override
    public Event assignCustomerEvent(Long customerId, Long eventId) {
        return eventRepository.findById(eventId)
                .map(event -> {
                    Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));
                    if (customer.getEvents().contains(event)) return event;
                    customer.getEvents().add(event);
                    customerRepository.save(customer);
                    return event;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }

    @Override
    public Event unnassignCustomerEvent(Long customerId, Long eventId) {
        return eventRepository.findById(eventId)
                .map(event -> {
                    Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));
                    customer.getEvents().remove(event);
                    customerRepository.save(customer);
                    return event;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }

    @Override
    @Transactional
    public void deleteEvent(Long eventId) {

        eventRepository.deleteById(eventId);
    }
}
