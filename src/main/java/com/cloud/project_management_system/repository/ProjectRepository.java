package com.cloud.project_management_system.repository;

import com.cloud.project_management_system.model.Project;
import com.cloud.project_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
//  List<Project> findByOwner(User user);

  List<Project> findByNameContainingAndTeamContains(String name, User user);

//  @Query("SELECT p from Project p join p.team t where t=:user")
//  List<Project> findByTeam(@Param("user")User user);

  List<Project> findByTeamContainingOrOwner(User user, User owner);
}
