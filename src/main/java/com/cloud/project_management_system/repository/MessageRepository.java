package com.cloud.project_management_system.repository;

import com.cloud.project_management_system.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
