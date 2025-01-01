package com.cloud.project_management_system.repository;

import com.cloud.project_management_system.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
