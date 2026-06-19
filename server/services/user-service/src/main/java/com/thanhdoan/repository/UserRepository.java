package com.thanhdoan.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhdoan.enums.UserRole;
import com.thanhdoan.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  Set<User> findByRole(UserRole role);
}
