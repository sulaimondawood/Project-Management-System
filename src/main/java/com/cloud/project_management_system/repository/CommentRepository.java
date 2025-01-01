package com.cloud.project_management_system.repository;

import com.cloud.project_management_system.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  Optional<List<Comment>> findByIssueId(Long issueId);
}
