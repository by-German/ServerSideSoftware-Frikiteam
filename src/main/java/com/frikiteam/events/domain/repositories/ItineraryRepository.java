package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
}
