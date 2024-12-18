package com.cloud.project_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Issue {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  private User assignees;
}
