package com.cloud.project_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subscription {

  @Id
  @GeneratedValue
  private Long id;

  private boolean isValid;
  private LocalDate subscriptionStartDate;
  private LocalDate subscriptionEndDate;
  private Plan planType;

  @OneToOne(mappedBy = "subscription")
  private User user;


}
