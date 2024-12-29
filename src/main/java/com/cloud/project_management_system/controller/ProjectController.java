package com.cloud.project_management_system.controller;

import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.model.Project;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.service.impl.ProjectServiceImpl;
import com.cloud.project_management_system.service.impl.UserServiceImpl;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Data
public class ProjectController {

  private final ProjectServiceImpl projectService;
  private final UserServiceImpl userService;

  @GetMapping
  public ResponseEntity<List<Project>> getAllProjects(
      @RequestHeader("Authorization") String authToken,
      @RequestParam(required = false) String category,
      @RequestParam(required = false) String tag
  ) throws ProjectException {
    User user = userService.findUserProfileByJwt(authToken);
    List<Project> projects = projectService.getProjectByTeam(user,category,tag);
    return new ResponseEntity<>(projects, HttpStatus.OK);
  }

  @PostMapping

  @GetMapping("/{projectId}")
  public ResponseEntity<Project> getProjectById(
      @PathVariable Long projectId
  ) throws  ProjectException{

    Project project = projectService.getProjectById(projectId);
    return new ResponseEntity<>(project, HttpStatus.OK);
  }

}
