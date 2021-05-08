package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {

}
