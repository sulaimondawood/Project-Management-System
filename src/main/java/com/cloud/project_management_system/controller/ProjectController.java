package com.cloud.project_management_system.controller;

import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.model.Project;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.response.MessageResponse;
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
  public ResponseEntity<Project> createProject(
      @RequestBody Project body,
      @RequestHeader("Authorization") String authJwt) throws  ProjectException
  {

    User user = userService.findUserProfileByJwt(authJwt);
    Project createProject= projectService.createProject(body,user);
    return new ResponseEntity<>(createProject, HttpStatus.CREATED);
  }

  @PatchMapping("/{projectId}")
  public ResponseEntity<Project> updateProject(
      @RequestBody Project project,
      @RequestHeader("Authorization") String authJwt,
      @PathVariable Long projectId
  ) throws ProjectException{

    User user = userService.findUserProfileByJwt(authJwt);
    Project updatedProject = projectService.updateProject(project,projectId);

    return new ResponseEntity<>(updatedProject, HttpStatus.OK);
  }

  @GetMapping("/{projectId}")
  public ResponseEntity<Project> getProjectById(
      @PathVariable Long projectId
  ) throws  ProjectException{

    Project project = projectService.getProjectById(projectId);
    return new ResponseEntity<>(project, HttpStatus.OK);
  }

  @DeleteMapping("/{projectId}")
  public ResponseEntity<MessageResponse> deleteProject(
      @PathVariable Long projectId,
      @RequestHeader("Authorization") String authJwt) throws ProjectException{

    MessageResponse messageResponse = new MessageResponse("Project deleted successfully");
    User user = userService.findUserProfileByJwt(authJwt);
    projectService.deleteProject(projectId,user.getId());
    return new ResponseEntity<>(messageResponse, HttpStatus.OK);
  }

}
