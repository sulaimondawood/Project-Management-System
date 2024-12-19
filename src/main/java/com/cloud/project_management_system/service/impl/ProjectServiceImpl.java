package com.cloud.project_management_system.service.impl;

import com.cloud.project_management_system.entity.Chat;
import com.cloud.project_management_system.entity.Project;
import com.cloud.project_management_system.entity.User;
import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.repository.ChatRepository;
import com.cloud.project_management_system.repository.ProjectRepository;
import com.cloud.project_management_system.service.interfaces.IProjectService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class ProjectServiceImpl implements IProjectService {

  private final ProjectRepository projectRepository;
  private final ChatRepository chatRepository;
  private final ChatServiceImpl chatService;

  @Override
  public Project createProject(Project project, User user) throws ProjectException {
    Project newProject = new Project();

    newProject.setOwner(user);
    newProject.setName(project.getName());
    newProject.setDescription(project.getDescription());
    newProject.setCategory(project.getCategory());
    newProject.setTags(project.getTags());
    newProject.getTeam().add(user);

    Project createdProject = projectRepository.save(newProject);

    Chat chat = new Chat();
    chat.setProject(createdProject);

    Chat projectChat = chatService.createChat(chat);
    newProject.setChat(projectChat);

    return newProject;
  }

  @Override
  public Project updateProject(Project project, Long projectId, User user) throws ProjectException {
    return null;
  }

  @Override
  public void deleteProject(Long projectId, Long userId) throws ProjectException {

  }

  @Override
  public List<Project> getAllProject() throws ProjectException {
    return List.of();
  }

  @Override
  public Project getProjectById(Long projectId) throws ProjectException {
    return null;
  }

  @Override
  public List<Project> getProjectByTeam(User user, String category, String tag) throws ProjectException {
    List<Project> projects = projectRepository.findByTeamContainingOrOwner(user,user);

    if(category != null){
      projects = projects.stream().filter(project -> project.getCategory().equals(category))
          .toList();
    }
    if(tag != null){
      projects = projects.stream().filter(project -> project.getTags().equals(tag))
          .toList();
    }
    return projects;
  }

  @Override
  public void addUserToProject(Long projectId, Long userId) throws ProjectException {

  }

  @Override
  public void removeUserFromProject(Long projectId, Long userId) throws ProjectException {

  }
}
