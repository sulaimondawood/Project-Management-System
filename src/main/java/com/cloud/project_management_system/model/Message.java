package com.cloud.project_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Message {

  @Id
  @GeneratedValue
  private Long id;

  private String message;
  private LocalDateTime createdAt;

  @ManyToOne
  private User sender;

  @ManyToOne
  private Chat chat;
}
