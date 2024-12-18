package com.cloud.project_management_system.controller;


import com.cloud.project_management_system.entity.User;
import com.cloud.project_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder){
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody User user){
    if(userRepository.existsByEmail(user.getEmail())){
      throw new RuntimeException("User already exists");
    }

    User newUser = new User();
    newUser.setEmail(user.getEmail());
    newUser.setFullName(user.getFullName());
    newUser.setPassword(passwordEncoder.encode(user.getPassword()));

    User createdUser = userRepository.save(newUser);
    return  new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }
}
