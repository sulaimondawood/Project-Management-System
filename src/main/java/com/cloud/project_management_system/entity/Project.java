package com.cloud.project_management_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String description;
  private String category;

  private List<String> tags = new ArrayList<>();

  @JsonIgnore
  @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private Chat chat;

  @JsonIgnore
  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Issue> issues= new ArrayList<>();

  @ManyToOne
  private User owner;

  @ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL)
  private List<User> team = new ArrayList<>();
}
