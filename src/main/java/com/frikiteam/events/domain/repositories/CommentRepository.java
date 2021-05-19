package com.frikiteam.events.domain.repositories;

import com.frikiteam.events.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
