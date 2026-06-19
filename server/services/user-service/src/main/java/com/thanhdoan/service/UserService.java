package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.model.User;

public interface UserService {
  User getUserByEmail(String email) throws Exception;

  User getUserById(Long id) throws Exception;

  List<User> getUsers() throws Exception;
}
