package com.cloud.project_management_system.service.interfaces;

import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.exceptions.ProjectException;

import java.util.List;

public interface IUserService {

  List<User> findAllUsers() throws ProjectException;

  User findUserProfileByJwt(String jwt) throws ProjectException;

  User findUserByEmail(String email) throws ProjectException;

  User findUserById(Long userId) throws  ProjectException;

  User updateUserProjectSize(User user, int number);


}
