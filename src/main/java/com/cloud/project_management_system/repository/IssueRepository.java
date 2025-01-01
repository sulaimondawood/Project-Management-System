package com.cloud.project_management_system.repository;

import com.cloud.project_management_system.model.Issue;
import com.cloud.project_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue,Long> {
  List<Issue> findByProjectId(Long projectId);
  List<Issue> findByAssignee(User user);
  List<Issue> findByTitleContainingIgnoreCase(String keyword);
}
