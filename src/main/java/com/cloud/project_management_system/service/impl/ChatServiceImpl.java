package com.cloud.project_management_system.service.impl;

import com.cloud.project_management_system.model.Chat;
import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.repository.ChatRepository;
import com.cloud.project_management_system.service.interfaces.IChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements IChatService {

  private final ChatRepository chatRepository;


  @Override
  public Chat createChat(Chat chat) throws ProjectException {
    return chatRepository.save(chat);
  }
}
