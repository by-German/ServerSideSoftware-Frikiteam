package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
