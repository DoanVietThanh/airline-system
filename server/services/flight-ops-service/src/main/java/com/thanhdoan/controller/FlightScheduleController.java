package com.thanhdoan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.request.FlightScheduleRequest;
import com.thanhdoan.payload.response.ApiResponse;
import com.thanhdoan.payload.response.FlightScheduleResponse;
import com.thanhdoan.service.FlightScheduleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flight-schedules")
public class FlightScheduleController {

  private final FlightScheduleService flightScheduleService;

  @PostMapping()
  public ResponseEntity<FlightScheduleResponse> createFlightSchedule(@RequestHeader("X-Airline-Id") Long airlineId,
      @Valid @RequestBody FlightScheduleRequest request)
      throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(flightScheduleService.createFlightSchedule(airlineId, request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<FlightScheduleResponse> getFlightScheduleById(@PathVariable Long id) throws Exception {
    return ResponseEntity.ok(flightScheduleService.getFlightScheduleById(id));
  }

  @GetMapping()
  public ResponseEntity<List<FlightScheduleResponse>> getFlightSchedulesByAirline(
      @RequestHeader("X-Airline-Id") Long airlineId) {
    return ResponseEntity.ok(flightScheduleService.getFlightSchedulesByAirline(airlineId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<FlightScheduleResponse> updateFlightSchedule(@PathVariable Long id,
      @Valid @RequestBody FlightScheduleRequest request) throws Exception {
    return ResponseEntity.ok(flightScheduleService.updateFlightSchedule(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteFlightSchedule(@PathVariable Long id) throws Exception {
    flightScheduleService.deleteFlightSchedule(id);
    ApiResponse response = new ApiResponse("Flight schedule deleted successfully");
    return ResponseEntity.ok(response);
  }
}
