package com.cloud.project_management_system.service;

import com.cloud.project_management_system.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsImpl implements UserDetailsService {
  private final UserRepository userRepository;

  public CustomUserDetailsImpl(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
  }
}
