package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
}
