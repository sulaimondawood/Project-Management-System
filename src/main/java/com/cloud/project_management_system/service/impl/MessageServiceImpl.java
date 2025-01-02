package com.cloud.project_management_system.service.impl;

import com.cloud.project_management_system.model.Chat;
import com.cloud.project_management_system.model.Message;
import com.cloud.project_management_system.model.Project;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.repository.MessageRepository;
import com.cloud.project_management_system.service.interfaces.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final ProjectServiceImpl projectService;
  private final UserServiceImpl userService;
  private final MessageRepository messageRepository;

  @Override
  public void sendMessage(Long senderId, Long projectId, String messageContent) {
    User user  = userService.findUserById(senderId);
    Chat chat = projectService.getProjectById(projectId).getChat();
    Message message = new Message();

    message.setMessage(messageContent);
    message.setSender(user);
    message.setChat(chat);
    message.setCreatedAt(LocalDateTime.now());

    messageRepository.save(message);

    chat.getMessages().add(message);

  }

  @Override
  public void editMessage(Long senderId, Long projectId, Long messageId, String message) {
    User user = userService.findUserById(senderId);

  }

  @Override
  public void deleteMessage(Long senderId, Long messageId, Long projectId) {

  }

  @Override
  public List<Message> getAllMessages(Long projectId) {
    return List.of();
  }
}
