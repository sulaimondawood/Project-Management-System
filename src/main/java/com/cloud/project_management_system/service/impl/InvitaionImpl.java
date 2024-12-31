package com.cloud.project_management_system.service.impl;

import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.model.Invitation;
import com.cloud.project_management_system.repository.InvitationRepository;
import com.cloud.project_management_system.service.interfaces.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvitaionImpl implements InvitationService {
  private final InvitationRepository invitationRepository;
  private final EmailServiceImpl emailService;

  @Override
  public void sendInvitation(String email, Long projectId) {
    String invitationToken = UUID.randomUUID().toString();
    String invitationLink = "http://localhost:3000/accept_invitation?token="+invitationToken;

    Invitation invitation = new Invitation();
    invitation.setEmail(email);
    invitation.setProjectId(projectId);
    invitation.setToken(invitationToken);

    invitationRepository.save(invitation);

    emailService.sendEmailWithToken(email,invitationLink);

  }

  @Override
  public Invitation acceptInvitation(String token, Long userId) {
    Invitation invitation = invitationRepository.findByToken(token).orElseThrow(()->new ProjectException("Invalid invitation Token"));
    return invitation;
  }

  @Override
  public void getTokenByUserEmail(String userEmail) {

  }

  @Override
  public void deleteToken(String token) {
    Invitation invitation = invitationRepository.findByToken(token).orElseThrow(()->new ProjectException("No token found"));
    invitationRepository.delete(invitation);
  }
}
