package com.cloud.project_management_system.service.interfaces;

public interface EmailService {
  String sendSimpleEmail(String userEmail,String body, String link);

  void sendEmailWithToken(String userEmail, String link);
}
