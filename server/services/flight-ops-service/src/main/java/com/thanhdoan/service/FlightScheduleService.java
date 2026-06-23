package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.payload.request.FlightScheduleRequest;
import com.thanhdoan.payload.response.FlightScheduleResponse;

public interface FlightScheduleService {
  FlightScheduleResponse createFlightSchedule(Long airlineId, FlightScheduleRequest request) throws Exception;

  FlightScheduleResponse getFlightScheduleById(Long id) throws Exception;

  List<FlightScheduleResponse> getFlightSchedulesByAirline(Long airlineId);

  FlightScheduleResponse updateFlightSchedule(Long id, FlightScheduleRequest request) throws Exception;

  void deleteFlightSchedule(Long id) throws Exception;
}
