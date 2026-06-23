package com.thanhdoan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.request.FlightInstanceRequest;
import com.thanhdoan.payload.response.ApiResponse;
import com.thanhdoan.payload.response.FlightInstanceResponse;
import com.thanhdoan.service.FlightInstanceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/flight-instances")
@RequiredArgsConstructor
public class FlightInstanceController {

  private final FlightInstanceService flightInstanceService;

  @PostMapping()
  public ResponseEntity<FlightInstanceResponse> createFlightInstance(
      @RequestHeader("Airline-Id") Long airlineId,
      @Valid @RequestBody FlightInstanceRequest flightInstanceRequest) throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(flightInstanceService.createFlightInstance(airlineId, flightInstanceRequest));
  }

  @GetMapping("/{id}")
  public ResponseEntity<FlightInstanceResponse> getFlightInstanceById(@PathVariable Long id) throws Exception {
    return ResponseEntity.ok(flightInstanceService.getFlightInstanceById(id));
  }

  @GetMapping("/airline")
  public ResponseEntity<Page<FlightInstanceResponse>> getFlightInstancesByAirlineId(
      @RequestHeader("Airline-Id") Long airlineId,
      @RequestParam(required = false) Long departureAirportId,
      @RequestParam(required = false) Long arrivalAirportId,
      @RequestParam(required = false) Long flightId,
      @RequestParam(required = false) LocalDate onDate,
      @PageableDefault(size = 10) Pageable pageable)
      throws Exception {
    return ResponseEntity.ok(flightInstanceService.getFlightInstancesByAirlineId(airlineId, departureAirportId,
        arrivalAirportId, flightId, onDate, pageable));
  }

  @PutMapping("/{id}")
  public ResponseEntity<FlightInstanceResponse> updateFlightInstance(
      @PathVariable Long id,
      @RequestBody FlightInstanceRequest flightInstanceRequest) throws Exception {
    return ResponseEntity.ok(flightInstanceService.updateFlightInstance(id, flightInstanceRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteFlightInstance(@PathVariable Long id)
      throws Exception {
    flightInstanceService.deleteFlightInstance(id);
    return ResponseEntity.ok(new ApiResponse("Flight instance deleted successfully"));
  }
}
