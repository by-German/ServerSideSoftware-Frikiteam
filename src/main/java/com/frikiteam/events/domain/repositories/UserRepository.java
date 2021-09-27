package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    User findByEmail(String email);
}
