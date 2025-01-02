package com.cloud.project_management_system.service.interfaces;

import com.cloud.project_management_system.model.Message;

import java.util.List;

public interface MessageService {
  void sendMessage(Long senderId, Long projectId, String message);

  void editMessage(Long senderId, Long projectId, Long messageId, String message);

  void deleteMessage(Long senderId, Long messageId, Long projectId);

  List<Message> getAllMessages();

  List<Message> getMessagesByProjectId(Long projectId);
}
