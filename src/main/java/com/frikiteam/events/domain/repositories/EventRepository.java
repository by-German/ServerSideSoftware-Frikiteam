package com.frikiteam.events.domain.repositories;


import com.frikiteam.events.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByName(String name);
}
