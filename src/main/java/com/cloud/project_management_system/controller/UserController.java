package com.cloud.project_management_system.controller;

import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserServiceImpl userService;

  @GetMapping("/profile")
  public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws ProjectException {
    System.out.println(jwt);
    User user = userService.findUserProfileByJwt(jwt);
    return ResponseEntity.ok(user);
  }
}
