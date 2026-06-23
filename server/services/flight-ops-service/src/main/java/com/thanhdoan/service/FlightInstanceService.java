package com.thanhdoan.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thanhdoan.payload.request.FlightInstanceRequest;
import com.thanhdoan.payload.response.FlightInstanceResponse;

public interface FlightInstanceService {
  FlightInstanceResponse createFlightInstance(Long airlineId, FlightInstanceRequest flightInstanceRequest)
      throws Exception;

  FlightInstanceResponse getFlightInstanceById(Long id) throws Exception;

  Page<FlightInstanceResponse> getFlightInstancesByAirlineId(Long airlineId, Long departureAirportId,
      Long arrivalAirportId, Long flightId, LocalDate onDate, Pageable pageable);

  FlightInstanceResponse updateFlightInstance(Long id, FlightInstanceRequest flightInstanceRequest) throws Exception;

  void deleteFlightInstance(Long id) throws Exception;
}
