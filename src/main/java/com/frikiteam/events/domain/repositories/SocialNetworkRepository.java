package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.SocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long> {
}
