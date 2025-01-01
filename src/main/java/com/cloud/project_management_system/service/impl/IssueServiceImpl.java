package com.cloud.project_management_system.service.impl;

import com.cloud.project_management_system.dto.IssueRequest;
import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.model.Issue;
import com.cloud.project_management_system.model.Project;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.repository.IssueRepository;
import com.cloud.project_management_system.service.interfaces.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService  {
  private final ProjectServiceImpl projectService;
  private final IssueRepository issueRepository;
  private final UserServiceImpl userService;

  @Override
  public void createIssue(IssueRequest issueRequest, Long userId) throws ProjectException {
    Project project = projectService.getProjectById(issueRequest.getProjectID());

    Issue issue = new Issue();
    issue.setTitle(issueRequest.getTitle());
    issue.setDescription(issueRequest.getDescription());
    issue.setStatus(issueRequest.getStatus());
    issue.setDueDate(issueRequest.getDueDate());
    issue.setPriority(issueRequest.getPriority());
    issue.setProjectID(issue.getProjectID());
    issue.setProject(project);

    issueRepository.save(issue);
    
  }

  @Override
  public void updateIssue(IssueRequest updateIssue, Long userId, Long issueId) throws ProjectException {

  }

  @Override
  public List<Issue> getAllIssues() throws ProjectException {
    return issueRepository.findAll();
  }

  @Override
  public Issue getIssueById(Long issueId) throws ProjectException {
    Issue issue = issueRepository.findById(issueId).orElseThrow(()->new ProjectException("Issue cannot be found"));
    return issue;
  }

  @Override
  public List<Issue> getIssuesByProjectId(Long projectId) throws ProjectException {
    List<Issue> issue = issueRepository.findByProjectId(projectId);
    return issue;
  }

  @Override
  public void deleteIssue(Long issueId, Long userId) throws ProjectException {
    getIssueById(issueId);
    issueRepository.deleteById(issueId);
  }

  @Override
  public List<Issue> searchIssues(String keyword, Long assigneeId) throws ProjectException {
    User user = userService.findUserById(assigneeId);
    if(assigneeId != null){
      return issueRepository.findByAssignee(user);
    }
    if(keyword!=null){
//      return issueRepository.findByTitleOrDescriptionAllIgnoreCaseContaining(keyword);
    }
    return List.of();
  }

  @Override
  public List<Issue> getAssigneeIssues(Long assigneeId) throws ProjectException {
    User user = userService.findUserById(assigneeId);
    return issueRepository.findByAssignee(user);
  }

  @Override
  public List<User> getIssueAssigness(Long issueId) throws ProjectException {
    Issue issue = getIssueById(issueId);
    User user = issue.getAssignee();
    return user != null? List.of(user):List.of();
  }

  @Override
  public Issue addUserToIssue(Long userId, Long issueId) throws ProjectException {
    Issue issue = getIssueById(issueId);
    User user = userService.findUserById(userId);

    issue.setAssignee(user);
    return issueRepository.save(issue);
  }

  @Override
  public void updateIssueStatus(Long issueId, String status) throws ProjectException {
    Issue issue = getIssueById(issueId);
    issue.setStatus(status);

    issueRepository.save(issue);
  }
}
