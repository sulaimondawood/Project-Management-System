package com.cloud.project_management_system.controller;


import com.cloud.project_management_system.configurations.JwtProvider;
import com.cloud.project_management_system.dto.LoginRequest;
import com.cloud.project_management_system.entity.User;
import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.repository.UserRepository;
import com.cloud.project_management_system.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtProvider jwtProvider;


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

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){

    AuthResponse response = new AuthResponse();

    String email = loginRequest.getEmail();
    String password = loginRequest.getPassword();
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
      User user = userRepository.findByEmail(email).orElseThrow(()-> new ProjectException("User does not exist"));
      String token = jwtProvider.generateToken(user);

      response.setToken(token);
      response.setMessage("Successfull");

    }catch(ProjectException e){
        response.setMessage("User does not exist");
    }
    catch (Exception e){
      response.setMessage("Something went wrong "  +e.getMessage() );
    }
    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

  }
}
