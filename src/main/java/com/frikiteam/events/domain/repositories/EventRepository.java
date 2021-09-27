package com.frikiteam.events.domain.repositories;


import com.frikiteam.events.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByNameContaining(String name);
}
