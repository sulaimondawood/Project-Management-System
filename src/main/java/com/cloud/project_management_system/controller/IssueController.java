package com.cloud.project_management_system.controller;

import com.cloud.project_management_system.model.Issue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

  @GetMapping
  public ResponseEntity<List<Issue>> getAllIssues(){

  }
}
