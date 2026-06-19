package com.thanhdoan.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thanhdoan.model.User;
import com.thanhdoan.repository.UserRepository;
import com.thanhdoan.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User getUserByEmail(String email) throws Exception {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new Exception("User not found with email: " + email);
    }
    return user;
  }

  @Override
  public User getUserById(Long id) throws Exception {
    return userRepository.findById(id)
        .orElseThrow(() -> new Exception("User not found with id: " + id));
  }

  @Override
  public List<User> getUsers() throws Exception {
    return userRepository.findAll();
  }
}
