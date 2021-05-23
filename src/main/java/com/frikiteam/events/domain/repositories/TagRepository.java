package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
