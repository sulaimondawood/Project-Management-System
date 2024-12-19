package com.cloud.project_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chat {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @OneToOne
  private Project project;

  @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Message> messages;

  @ManyToMany
  private List<User> users;
}
