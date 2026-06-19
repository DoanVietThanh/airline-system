package com.thanhdoan.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.thanhdoan.enums.UserRole;
import com.thanhdoan.model.User;
import com.thanhdoan.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DataInitializationComponent implements CommandLineRunner {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) {
    initializeAdminUser();
  }

  private void initializeAdminUser() {
    String adminUsername = "doanvietthanhhs@gmail.com";

    if (userRepository.findByEmail(adminUsername) == null) {
      User adminUser = new User();

      adminUser.setPassword(passwordEncoder.encode("doanvietthanhhs"));
      adminUser.setFullName("Doan Viet Thanh");
      adminUser.setEmail(adminUsername);
      adminUser.setRole(UserRole.ROLE_SYSTEM_ADMIN);

      userRepository.save(adminUser);
    }
  }
}
