package com.thanhdoan.payload.response;

import com.thanhdoan.payload.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
  private String jwt;
  private String message;
  private String title;
  private UserDto user;

}
