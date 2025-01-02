package com.cloud.project_management_system.repository;

import com.cloud.project_management_system.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
  List<Message> findByChatIdOrderByCreatedAtAsc(Long chatId);
}
