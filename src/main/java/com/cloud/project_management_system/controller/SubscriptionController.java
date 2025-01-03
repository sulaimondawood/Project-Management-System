package com.cloud.project_management_system.controller;

import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.model.Plan;
import com.cloud.project_management_system.model.Subscription;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.response.MessageResponse;
import com.cloud.project_management_system.service.impl.UserServiceImpl;
import com.cloud.project_management_system.service.interfaces.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

  private final SubscriptionService subscriptionService;
  private final UserServiceImpl userService;


  @GetMapping
  public ResponseEntity<List<Subscription>> getAllSubscriptions(){
    List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();

    return ResponseEntity.ok(subscriptions);
  }

  @GetMapping("/user")
  public ResponseEntity<Subscription> getUserSubscription(
      @RequestHeader("Authorization") String jwt
  ) throws ProjectException  {
    User user = userService.findUserProfileByJwt(jwt);
    Subscription subscription = subscriptionService.getUserSubscription(user.getId());

    return ResponseEntity.ok(subscription);
  }

  @PatchMapping("/upgrade")
  public ResponseEntity<MessageResponse> upgradeUserSubscription(@RequestParam Plan planType,
                                                                 @RequestHeader("Authorization") String jwt){
    User user = userService.findUserProfileByJwt(jwt);
    Subscription subscription = subscriptionService.upgradeSubscription(user.getId(),planType);
    MessageResponse res = new MessageResponse("Subcription upgraded");

    return ResponseEntity.ok(res);
  }
}
