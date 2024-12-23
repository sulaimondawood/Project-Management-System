package com.cloud.project_management_system.service.impl;

import com.cloud.project_management_system.configurations.JwtProvider;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.repository.UserRepository;
import com.cloud.project_management_system.service.interfaces.IUserService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserServiceImpl implements IUserService {

  private final UserRepository userRepository;
  private final JwtProvider jwtProvider;

  @Override
  public List<User> findAllUsers() throws ProjectException {
    return userRepository.findAll();
  }

  @Override
  public User findUserProjectByJwt(String jwt) throws ProjectException {
    String email = jwtProvider.extractEmail(jwt);
   return findUserByEmail(email);
  }

  @Override
  public User findUserByEmail(String email) throws ProjectException {
   return  userRepository.findByEmail(email).orElseThrow(()->new ProjectException("user not be found"));
  }

  @Override
  public User findUserById(Long userId) throws ProjectException {
    return userRepository.findById(userId).orElseThrow(()->new ProjectException("user not found"));
  }

  @Override
  public User updateUserProjectSize(User user, int number) {
    user.setProjectSize(user.getProjectSize()+ number);
    return userRepository.save(user);
  }
}
