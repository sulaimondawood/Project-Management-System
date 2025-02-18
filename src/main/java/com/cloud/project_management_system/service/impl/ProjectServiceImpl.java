package com.cloud.project_management_system.service.impl;

import com.cloud.project_management_system.model.Chat;
import com.cloud.project_management_system.model.Project;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.repository.ChatRepository;
import com.cloud.project_management_system.repository.ProjectRepository;
import com.cloud.project_management_system.repository.UserRepository;
import com.cloud.project_management_system.service.interfaces.IProjectService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ProjectServiceImpl implements IProjectService {

  private final ProjectRepository projectRepository;
  private final UserRepository userRepository;
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
  public Project updateProject(Project project, Long projectId) throws ProjectException {
    Project updateProject = getProjectById(projectId);

    if(project.getTags() != null) updateProject.setTags(project.getTags());
    if(project.getName() != null) updateProject.setName(project.getName());
    if(project.getDescription() != null) updateProject.setDescription(project.getDescription());
    if(project.getCategory() != null) updateProject.setCategory(project.getCategory());

    return  projectRepository.save(updateProject);
  }

  @Override
  public void deleteProject(Long projectId, Long userId) throws ProjectException {
    getProjectById(projectId);
    projectRepository.deleteById(projectId);

  }

  @Override
  public List<Project> getAllProjects() throws ProjectException {
    return projectRepository.findAll();
  }

  @Override
  public Project getProjectById(Long projectId) throws ProjectException {
    return projectRepository.findById(projectId).orElseThrow(()->new ProjectException("Project not found"));
  }

  @Override
  public List<Project> getProjectByTeam(User user, String category, String tag) throws ProjectException {
    List<Project> projects = projectRepository.findByTeamContainingOrOwner(user,user);

    if(category != null){
      projects = projects.stream().filter(project -> project.getCategory().equals(category))
          .toList();
    }
    if(tag != null){
      projects = projects.stream().filter(project -> project.getTags().contains(tag))
          .toList();
    }
    return projects;
  }

  @Override
  public void addUserToProject(Long projectId, Long userId) throws ProjectException {
    Project project = getProjectById(projectId);
    User user = userRepository.findById(projectId).orElseThrow(()->new ProjectException("User not found"));

    if(!project.getTeam().contains(user)){
      project.getTeam().add(user);
      project.getChat().getUsers().add(user);

      projectRepository.save(project);
    }

  }

  @Override
  public void removeUserFromProject(Long projectId, Long userId) throws ProjectException {
    Project project = getProjectById(projectId);
    User user = userRepository.findById(userId).orElseThrow(()->new ProjectException("User not found"));

    if(project.getTeam().contains(user)){
      project.getTeam().remove(user);
      project.getChat().getUsers().remove(user);

      projectRepository.save(project);
    }

  }

  @Override
  public Chat getChatByProjectId(Long projectId) throws ProjectException {
    Project project = getProjectById(projectId);
    return project.getChat();
  }

  @Override
  public List<Project> searchProject(String keyword, User user) throws ProjectException {
    String partialKeyword = "%" + keyword + "%";
    return projectRepository.findByNameContainingAndTeamContains(partialKeyword, user);
  }

}
