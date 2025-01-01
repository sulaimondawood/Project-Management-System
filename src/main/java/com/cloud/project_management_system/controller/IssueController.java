package com.cloud.project_management_system.controller;

import com.cloud.project_management_system.dto.IssueRequest;
import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.model.Issue;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.response.MessageResponse;
import com.cloud.project_management_system.service.impl.UserServiceImpl;
import com.cloud.project_management_system.service.interfaces.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {
  private final IssueService issueService;
  private final UserServiceImpl userService;
  @GetMapping
  public ResponseEntity<List<Issue>> getAllIssues(){
    try {
      List<Issue> issues = issueService.getAllIssues();
      return ResponseEntity.ok(issues);
    }catch (ProjectException e){
      return ResponseEntity.badRequest().build();
    }catch (Exception e){
      return  ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping
  public ResponseEntity<MessageResponse> createIssue(@RequestBody IssueRequest body, @RequestHeader("Authorization") String jwt){
    User user = userService.findUserProfileByJwt(jwt);
    MessageResponse messageResponse = new MessageResponse("Issue created");
    issueService.createIssue(body, user.getId());
    return ResponseEntity.ok(messageResponse);
  }

  @PutMapping("/{issueId}")
  public ResponseEntity<MessageResponse> createIssue(@RequestBody IssueRequest req,
                                                     @PathVariable Long issueId,
                                                     @RequestHeader("Authorization") String jwt){
    User user = userService.findUserProfileByJwt(jwt);
    issueService.updateIssue(req,user.getId(),issueId);
    MessageResponse messageResponse = new MessageResponse("Issue updated");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(messageResponse);
  }

  @GetMapping("/{issueId}")
  public ResponseEntity<Issue> getIssue(@PathVariable Long issueId){

    Issue issue = issueService.getIssueById(issueId);
    return ResponseEntity.ok(issue);
  }

  @GetMapping("/project/{projectId}")
  public  ResponseEntity<List<Issue>> getIssuesByProjectId(@PathVariable Long projectId){
    List<Issue> issues = issueService.getIssuesByProjectId(projectId);
    return  ResponseEntity.ok(issues);
  }

  @DeleteMapping("/{issueId}")
  public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId, @RequestHeader String jwt){
    User user = userService.findUserProfileByJwt(jwt);
    issueService.deleteIssue(issueId,user.getId());
    MessageResponse res = new MessageResponse("Issue deleted");
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
  }

  @GetMapping("/search")
  public ResponseEntity<List<Issue>> searchIssues(@RequestBody String keyword,
                                                  @RequestHeader("Authorization") String jwt){
    User user = userService.findUserProfileByJwt(jwt);
    List<Issue> issues = issueService.searchIssues(keyword,user.getId());
    return ResponseEntity.ok(issues);
  }

  @GetMapping("/assigneee/")
  public ResponseEntity<List<Issue>> getAssigneeIssues(
      @RequestHeader("Authorization") String jwt
  ){
    User user = userService.findUserProfileByJwt(jwt);
    List<Issue> issues = issueService.getAssigneeIssues(user.getId());
    return ResponseEntity.ok(issues);
  }

  @GetMapping("/{issueId}/assignees")
  public ResponseEntity<List<User>> getIssueAssignees(@PathVariable Long issueId){
    List<User> users = issueService.getIssueAssigness(issueId);
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{issueId}/users")
  public  ResponseEntity<Issue> addUserToIssue(@PathVariable("issueId") Long issueId,
                                               @RequestHeader("Authorization") String jwt){
    Long user = userService.findUserProfileByJwt(jwt).getId();
    Issue issue = issueService.addUserToIssue(user, issueId);
    return ResponseEntity.ok(issue);
  }

  @PutMapping("/{issueId}/status")
  public ResponseEntity<MessageResponse> updateIssueStatus(@RequestBody String status,
                                                           @PathVariable Long issueId){
    issueService.updateIssueStatus(issueId,status);
    MessageResponse res = new MessageResponse("Status updated");
    return ResponseEntity.ok(res);
  }


}
