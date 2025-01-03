package com.cloud.project_management_system.repository;

import com.cloud.project_management_system.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
  Optional<Subscription> findByUserId(Long userId);
}
