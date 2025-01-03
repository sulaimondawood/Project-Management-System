package com.cloud.project_management_system.repository;

import com.cloud.project_management_system.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
}
