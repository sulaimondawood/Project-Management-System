package com.cloud.project_management_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitationRequest {
  private String email;
  private Long projectId;
}
