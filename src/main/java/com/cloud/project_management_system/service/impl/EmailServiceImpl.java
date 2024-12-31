package com.cloud.project_management_system.service.impl;

import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.service.interfaces.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
  private final JavaMailSender javaMailSender;
  private final String subject ="Project Invitation from ";
  private final String fromUser = "Dawood Sulaimon";

  @Override
  public String sendSimpleEmail(String userEmail, String body, String link) {
    try {
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      simpleMailMessage.setFrom(fromUser);
      simpleMailMessage.setTo(userEmail);
      simpleMailMessage.setText(body);
      simpleMailMessage.setSubject(subject + userEmail);

      javaMailSender.send(simpleMailMessage);
      return "Mail sent successfully";
    }catch (ProjectException e){
      return "Error occured while sending the mail";
    }
  }

  @Override
  public void sendEmailWithToken(String userEmail, String link) {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper;

    try {
      mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
      mimeMessageHelper.setFrom(fromUser);
      mimeMessageHelper.setTo(userEmail);
      mimeMessageHelper.setSubject(subject);
      mimeMessageHelper.setText("Click the link to accept the project invitation");

      javaMailSender.send(mimeMessage);
    }catch (MessagingException e){
      throw new ProjectException("Error occured while sending the mail");
    }
  }
}
