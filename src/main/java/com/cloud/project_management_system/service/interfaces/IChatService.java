package com.cloud.project_management_system.service.interfaces;

import com.cloud.project_management_system.entity.Chat;
import com.cloud.project_management_system.exceptions.ProjectException;

public interface IChatService {
  Chat createChat(Chat chat)throws ProjectException;
}