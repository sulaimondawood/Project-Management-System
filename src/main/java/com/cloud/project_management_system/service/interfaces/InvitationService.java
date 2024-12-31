package com.cloud.project_management_system.service.interfaces;

import com.cloud.project_management_system.model.Invitation;

public interface InvitationService {
  void sendInvitation(String email, Long projectId);

  Invitation acceptInvitation(String token, Long userId);

  String getTokenByUserEmail(String userEmail);

  void deleteToken(String token);

}
