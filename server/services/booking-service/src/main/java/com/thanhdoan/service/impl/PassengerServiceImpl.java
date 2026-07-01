package com.thanhdoan.service.impl;

import com.thanhdoan.repository.PassengerRepository;
import com.thanhdoan.payload.request.PassengerRequest;
import com.thanhdoan.service.PassengerService;

import org.springframework.stereotype.Service;

import com.thanhdoan.mapper.PassengerMapper;
import com.thanhdoan.model.Passenger;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {
  private final PassengerRepository passengerRepository;

  @Override
  public Passenger createPassenger(PassengerRequest passengerRequest, Long userId) {

    Passenger passenger = PassengerMapper.toEntity(passengerRequest);
    passenger.setPrimaryUserId(userId);
    return passengerRepository.save(passenger);
  }
}
