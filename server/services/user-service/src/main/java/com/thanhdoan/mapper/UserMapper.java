package com.thanhdoan.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.thanhdoan.model.User;
import com.thanhdoan.payload.dto.UserDto;

public class UserMapper {

  private UserMapper() {
  }

  public static UserDto toDTO(User user) {
    UserDto dto = new UserDto();
    dto.setId(user.getId());
    dto.setEmail(user.getEmail());
    dto.setFullName(user.getFullName());
    dto.setPhone(user.getPhone());
    dto.setRole(user.getRole());
    dto.setLastLogin(user.getLastLogin());
    return dto;
  }

  public static List<UserDto> toDTOList(List<User> users) {
    return users.stream()
        .map(UserMapper::toDTO)
        .collect(Collectors.toList());
  }

  public static Set<UserDto> toDTOSet(Set<User> users) {
    return users.stream()
        .map(UserMapper::toDTO)
        .collect(Collectors.toSet());
  }
}
