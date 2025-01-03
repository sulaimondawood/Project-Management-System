package com.cloud.project_management_system.service.interfaces;

import com.cloud.project_management_system.model.Plan;
import com.cloud.project_management_system.model.Subscription;

import java.util.List;

public interface SubscriptionService {
  void createSubscription(Long userId);

  List<Subscription> getAllSubscriptions();

  Subscription getUserSubscription(Long userId);

  Subscription upgradeSubscription(Long userId, Plan planType);

  boolean isSubscriptionValid(Subscription subscription);

}
