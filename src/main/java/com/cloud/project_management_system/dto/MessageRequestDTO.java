package com.cloud.project_management_system.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageRequestDTO {
  private Long projectId;

  private String message;

}
