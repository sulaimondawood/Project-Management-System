package com.cloud.project_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

  @Id
  @GeneratedValue
  private Long id;

  private String comment;
  private LocalDateTime createdAt;

  @ManyToOne
  private User user;

  @ManyToOne
  private Issue issue;
}
