package com.thanhdoan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.dto.UserDto;
import com.thanhdoan.payload.request.LoginRequest;
import com.thanhdoan.payload.response.AuthResponse;
import com.thanhdoan.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<AuthResponse> signup(
      @Valid @RequestBody UserDto req) throws Exception {
    AuthResponse response = authService.signup(req);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(
      @Valid @RequestBody LoginRequest req) throws Exception {
    AuthResponse response = authService.login(req.getEmail(), req.getPassword());
    return ResponseEntity.ok(response);
  }
}
