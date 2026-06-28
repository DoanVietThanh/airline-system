package com.thanhdoan.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.request.FlightInstanceCabinRequest;
import com.thanhdoan.payload.response.FlightInstanceCabinResponse;
import com.thanhdoan.service.FlightInstanceCabinService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/flight-instance-cabin")
@RequiredArgsConstructor
public class FlightInstanceCabinController {

  private final FlightInstanceCabinService flightInstanceCabinService;

  @PostMapping
  public ResponseEntity<FlightInstanceCabinResponse> createFlightInstanceCabin(
      @Valid @RequestBody FlightInstanceCabinRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(flightInstanceCabinService.createFlightInstanceCabin(request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<FlightInstanceCabinResponse> getFlightInstanceCabinById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(flightInstanceCabinService.getFlightInstanceCabinById(id));
  }

  @GetMapping("/flight-instance/{flightInstanceId}/cabin-class/{cabinClassId}")
  public ResponseEntity<FlightInstanceCabinResponse> getByFlightInstanceIdAndCabinClassId(
      @PathVariable Long flightInstanceId, @PathVariable Long cabinClassId) throws Exception {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(flightInstanceCabinService.getByFlightInstanceIdAndCabinClassId(flightInstanceId, cabinClassId));
  }

  @GetMapping("/flight-instance/{flightInstanceId}")
  public ResponseEntity<Page<FlightInstanceCabinResponse>> getByFlightInstanceId(@PathVariable Long flightInstanceId,
      Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(flightInstanceCabinService.getByFlightInstanceId(flightInstanceId, pageable));
  }

  @PutMapping("/{id}")
  public ResponseEntity<FlightInstanceCabinResponse> updateFlightInstanceCabin(@PathVariable Long id,
      @Valid @RequestBody FlightInstanceCabinRequest request) throws Exception {
    return ResponseEntity.status(HttpStatus.OK)
        .body(flightInstanceCabinService.updateFlightInstanceCabin(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFlightInstanceCabin(@PathVariable Long id) throws Exception {
    flightInstanceCabinService.deleteFlightInstanceCabin(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
