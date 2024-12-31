package com.cloud.project_management_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IssueRequest {
  private String title;
  private String description;
  private String Status;
  private Long issueId;
  private LocalDate dueDate;
  private String priority;

}
