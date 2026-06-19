package com.thanhdoan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.mapper.UserMapper;
import com.thanhdoan.model.User;
import com.thanhdoan.payload.dto.UserDto;
import com.thanhdoan.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/api/users/profile")
  public ResponseEntity<UserDto> getUserProfile(
      @RequestHeader("X-User-Email") String email) throws Exception {
    User user = userService.getUserByEmail(email);
    return ResponseEntity.ok(UserMapper.toDTO(user));
  }

  @GetMapping("/api/users/{userId}")
  public ResponseEntity<UserDto> getUserById(
      @PathVariable Long userId) throws Exception {
    User user = userService.getUserById(userId);
    return ResponseEntity.ok(UserMapper.toDTO(user));
  }

  @GetMapping("/api/users")
  public ResponseEntity<List<UserDto>> getUsers() throws Exception {
    List<User> users = userService.getUsers();
    return ResponseEntity.ok(UserMapper.toDTOList(users));
  }
}
