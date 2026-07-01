package com.thanhdoan.service;

import com.thanhdoan.model.Passenger;
import com.thanhdoan.payload.request.PassengerRequest;

public interface PassengerService {

  Passenger createPassenger(PassengerRequest passengerRequest, Long userId);
}
