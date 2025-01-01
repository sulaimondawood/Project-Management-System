package com.cloud.project_management_system.dto;

import com.cloud.project_management_system.model.Project;
import com.cloud.project_management_system.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class IssueDTO {
  private Long id;
  private String title;
  private String description;
  private String Status;
  private Long projectID;
  private LocalDate dueDate;
  private String priority;
  private List<String> tags = new ArrayList<>();
  private User assignee;
  private Project project;
}
