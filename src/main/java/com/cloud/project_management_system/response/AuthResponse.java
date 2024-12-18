package com.cloud.project_management_system.response;

import lombok.Data;

@Data
public class AuthResponse {
  private  String token;
  private String message;
}
