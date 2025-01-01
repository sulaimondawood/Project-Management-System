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


}
