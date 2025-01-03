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
    try {

    Subscription subscription = new Subscription();
    subscription.setUser(user);
    subscription.setPlanType(Plan.FREE);
    subscription.setValid(true);
    subscription.setSubscriptionStartDate(LocalDate.now());
    subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));

    subscriptionRepository.save(subscription);
    }catch (ProjectException e){
      throw new ProjectException("Something went wrong");
    }catch (Exception e){
      throw new ProjectException("Internal server error");
    }


  }

  @Override
  public List<Subscription> getAllSubscriptions() {
    return subscriptionRepository.findAll();
  }

  @Override
  public Subscription getUserSubscription(Long userId) {
    Subscription subscription = subscriptionRepository.findByUserId(userId).orElseThrow(()->new ProjectException("You haven't subscribed yet"));

    if(!isSubscriptionValid(subscription)){
      subscription.setPlanType(Plan.FREE);
      subscription.setSubscriptionStartDate(LocalDate.now());
      subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
    }
    subscriptionRepository.save(subscription);
    return subscription;
  }

  @Override
  public Subscription upgradeSubscription(Long userId, Plan planType) {
    Subscription subscription = getUserSubscription(userId);
    subscription.setPlanType(planType);
    subscription.setSubscriptionStartDate(LocalDate.now());

    if(planType.equals(Plan.FREE)){
      subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
    }else if(planType.equals(Plan.MONTHLY)){
      subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
    }else{
      subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
    }
    return subscription;
  }

  @Override
  public boolean isSubscriptionValid(Subscription subscription) {
    if(subscription.getPlanType().equals(Plan.FREE)) return true;

    LocalDate currentDate = LocalDate.now();
    LocalDate endDate = subscription.getSubscriptionEndDate();

    return endDate.equals(currentDate) || endDate.isAfter(currentDate);
  }
}
