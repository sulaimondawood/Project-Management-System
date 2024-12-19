package com.cloud.project_management_system.service.interfaces;

import com.cloud.project_management_system.entity.Project;
import com.cloud.project_management_system.entity.User;
import com.cloud.project_management_system.exceptions.ProjectException;

import java.util.List;

public interface IProjectService {

  Project createProject(Project project, User user) throws ProjectException;

  Project updateProject(Project project, Long projectId, User user) throws ProjectException;

  void deleteProject(Long projectId, Long userId) throws ProjectException;

  List<Project> getAllProject() throws  ProjectException;

  Project getProjectById(Long projectId) throws  ProjectException;

  List<Project> getProjectByTeam(User user, String category, String tag) throws ProjectException;

  void addUserToProject(Long projectId, Long userId) throws  ProjectException;

  void removeUserFromProject(Long projectId, Long userId) throws ProjectException;
}