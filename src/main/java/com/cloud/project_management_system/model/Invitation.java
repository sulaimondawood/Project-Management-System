package com.cloud.project_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Invitation {

  @Id
  @GeneratedValue
  private Long id;

  private String email;
  private String token;
  private Long projectId;

}
