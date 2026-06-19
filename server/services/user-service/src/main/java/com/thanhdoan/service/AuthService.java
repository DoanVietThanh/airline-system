package com.thanhdoan.service;

import com.thanhdoan.payload.dto.UserDto;
import com.thanhdoan.payload.response.AuthResponse;

public interface AuthService {
  AuthResponse signup(UserDto userDto) throws Exception;

  AuthResponse login(String email, String password) throws Exception;

}