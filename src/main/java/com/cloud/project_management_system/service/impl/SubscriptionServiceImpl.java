package com.cloud.project_management_system.service.impl;

import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.model.Plan;
import com.cloud.project_management_system.model.Subscription;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.repository.SubscriptionRepository;
import com.cloud.project_management_system.service.interfaces.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;
  private final UserServiceImpl userService;

  @Override
  public void createSubscription(Long userId) {
    User user = userService.findUserById(userId);
    Subscription subscription = new Subscription();
    subscription.setUser(user);
    subscription.setPlanType(Plan.FREE);
    subscription.setValid(true);
    subscription.setSubscriptionStartDate(LocalDate.now());
    subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));

    subscriptionRepository.save(subscription);

  }

  @Override
  public List<Subscription> getAllSubscriptions() {
    return subscriptionRepository.findAll();
  }

  @Override
  public Subscription getUserSubscription(Long userId) {
    Subscription subscription = subscriptionRepository.findByUserId(userId).orElseThrow(()->new ProjectException("You haven't subscribed yet"));
    return subscription;
  }

  @Override
  public Subscription upgradeSubscription(Long userId, Plan planType) {

    return null;
  }

  @Override
  public boolean isSubscriptionValid(Subscription subscription) {
    return false;
  }
}
