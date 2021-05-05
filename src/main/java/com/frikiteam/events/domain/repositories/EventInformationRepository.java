package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.EventInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventInformationRepository extends JpaRepository<EventInformation, Long> {

}
