package com.cloud.project_management_system.repository;

import com.cloud.project_management_system.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
