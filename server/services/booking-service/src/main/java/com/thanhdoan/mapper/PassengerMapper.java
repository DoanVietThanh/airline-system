package com.thanhdoan.mapper;

import com.thanhdoan.model.Passenger;
import com.thanhdoan.payload.request.PassengerRequest;
import com.thanhdoan.payload.response.PassengerResponse;

public class PassengerMapper {
  public static Passenger toEntity(PassengerRequest passengerRequest) {
    return Passenger.builder()
        .firstName(passengerRequest.getFirstName())
        .lastName(passengerRequest.getLastName())
        .email(passengerRequest.getEmail())
        .phone(passengerRequest.getPhone())
        .dateOfBirth(passengerRequest.getDateOfBirth())
        .gender(passengerRequest.getGender())
        .nationality(passengerRequest.getNationality())
        .build();
  }

  public static PassengerResponse toResponse(Passenger passenger) {
    return PassengerResponse.builder()
        .id(passenger.getId())
        .firstName(passenger.getFirstName())
        .lastName(passenger.getLastName())
        .email(passenger.getEmail())
        .phone(passenger.getPhone())
        .dateOfBirth(passenger.getDateOfBirth())
        .gender(passenger.getGender())
        .nationality(passenger.getNationality())
        .primaryUserId(passenger.getPrimaryUserId())
        .isActive(passenger.getIsActive())
        .age(passenger.getAge())
        .isAdult(passenger.isAdult())
        .fullName(passenger.getFullName())
        .createdAt(passenger.getCreatedAt())
        .updatedAt(passenger.getUpdatedAt())
        .build();
  }
}
