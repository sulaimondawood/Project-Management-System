package com.cloud.project_management_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {

  @Id
  @GeneratedValue
  private Long id;

  private String title;
  private String description;
  private String Status;
  private Long issueId;
  private LocalDate dueDate;
  private String priority;
  private List<String> tags = new ArrayList<>();

  @OneToMany(mappedBy = "issue", orphanRemoval = true,cascade = CascadeType.ALL)
  private List<Comment> comments;

  @JsonIgnore
  @ManyToOne
  private User assignee;

  @JsonIgnore
  @ManyToOne
  private Project project;
}
