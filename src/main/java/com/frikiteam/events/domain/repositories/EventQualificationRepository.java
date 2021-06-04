package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.EventQualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventQualificationRepository extends JpaRepository<EventQualification, Long> {
}


