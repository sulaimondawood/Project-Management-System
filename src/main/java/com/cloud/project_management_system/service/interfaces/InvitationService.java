package com.cloud.project_management_system.service.interfaces;

public interface InvitationService {
  void sendInvitation(String email, Long projectId);

  void acceptInvitation(String token, Long userId);

  void getTokenByUserEmail(String userEmail);

  void deleteToken(String token);

}
