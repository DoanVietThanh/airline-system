package com.thanhdoan.payload.dto;

import java.time.LocalDateTime;

import com.thanhdoan.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private Long id;
  private String email;
  private String password;
  private String fullName;
  private String phone;
  private UserRole role;
  private LocalDateTime lastLogin;

}
