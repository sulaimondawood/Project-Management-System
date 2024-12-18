package com.cloud.project_management_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Long id;

  private String fullName;

  private String email;

  private String password;

  @JsonIgnore
  @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
  private List<Issue> assignedIssues = new ArrayList<>();

  @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Project> project;

  @ManyToMany
  private List<Project> projects;

  private int projectSize;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public String getPassword(){
    return password;
  }
}


