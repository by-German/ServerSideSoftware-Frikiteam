package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
