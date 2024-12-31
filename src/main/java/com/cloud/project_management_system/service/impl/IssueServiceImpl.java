package com.cloud.project_management_system.service.impl;

import com.cloud.project_management_system.dto.IssueRequest;
import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.model.Issue;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.service.interfaces.IssueService;

import java.util.List;
import java.util.Optional;

public class IssueServiceImpl implements IssueService  {
  @Override
  public void createIssue(IssueRequest issueRequest, Long userId) throws ProjectException {

    
  }

  @Override
  public void updateIssue(IssueRequest updateIssue, Long userId, Long issueId) throws ProjectException {

  }

  @Override
  public List<Issue> getAllIssues() throws ProjectException {
    return List.of();
  }

  @Override
  public Optional<Issue> getIssueById(Long issueId) throws ProjectException {
    return Optional.empty();
  }

  @Override
  public List<Issue> getIssuesByProjectId(Long projectId) throws ProjectException {
    return List.of();
  }

  @Override
  public void deleteIssue(Long issueId) throws ProjectException {

  }

  @Override
  public List<Issue> searchIssues(String title, String status, String priority, Long assigneeId) throws ProjectException {
    return List.of();
  }

  @Override
  public List<Issue> getAssigneeIssues(Long assigneeId) throws ProjectException {
    return List.of();
  }

  @Override
  public List<User> getIssueAssigness(Long issueId) throws ProjectException {
    return List.of();
  }

  @Override
  public Issue addUserToIssue(Long userId, Long issueId) throws ProjectException {
    return null;
  }

  @Override
  public void updateIssueStatus(Long issueId, String status) throws ProjectException {

  }
}
