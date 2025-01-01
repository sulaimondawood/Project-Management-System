package com.cloud.project_management_system.service.interfaces;

import com.cloud.project_management_system.dto.IssueRequest;
import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.model.Issue;
import com.cloud.project_management_system.model.User;

import java.util.List;
import java.util.Optional;

public interface IssueService {
  void createIssue(IssueRequest issueRequest, Long userId) throws ProjectException;

  void updateIssue(IssueRequest updateIssue, Long userId, Long issueId) throws ProjectException;

  List<Issue> getAllIssues() throws ProjectException;

  Issue getIssueById(Long issueId) throws ProjectException;

  List<Issue> getIssuesByProjectId(Long projectId) throws ProjectException;

  void deleteIssue(Long issueId, Long userId) throws ProjectException;

  List<Issue> searchIssues(String keyword, Long assigneeId) throws ProjectException;

  List<Issue> getAssigneeIssues(Long assigneeId) throws ProjectException;

  List<User> getIssueAssigness(Long issueId) throws ProjectException;

  Issue addUserToIssue(Long userId, Long issueId) throws ProjectException;

  void updateIssueStatus(Long issueId, String status) throws ProjectException;
}
