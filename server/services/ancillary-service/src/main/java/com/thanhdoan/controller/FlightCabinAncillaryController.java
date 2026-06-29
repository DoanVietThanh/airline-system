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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.enums.AncillaryType;
import com.thanhdoan.payload.request.FlightCabinAncillaryRequest;
import com.thanhdoan.payload.response.FlightCabinAncillaryResponse;
import com.thanhdoan.service.FlightCabinAncillaryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flight-cabin-ancillaries")
public class FlightCabinAncillaryController {

  private final FlightCabinAncillaryService flightCabinAncillaryService;

  @PostMapping
  public ResponseEntity<FlightCabinAncillaryResponse> createFlightCabinAncillary(
      @Valid @RequestBody FlightCabinAncillaryRequest flightCabinAncillaryRequest) throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(flightCabinAncillaryService.createFlightCabinAncillary(flightCabinAncillaryRequest));
  }

  @GetMapping("/{id}")
  public ResponseEntity<FlightCabinAncillaryResponse> getFlightCabinAncillaryById(
      @PathVariable Long id) throws Exception {
    return ResponseEntity.ok(flightCabinAncillaryService.getFlightCabinAncillaryById(id));
  }

  @GetMapping("/flight/{flightId}/cabin/{cabinClassId}")
  public ResponseEntity<List<FlightCabinAncillaryResponse>> getFlightCabinAncillariesByFlightIdAndCabinClassId(
      @PathVariable Long flightId, @PathVariable Long cabinClassId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(flightCabinAncillaryService.getByFlightIdAndCabinClassId(flightId, cabinClassId));
  }

  @GetMapping("/flight/{flightId}/cabin/{cabinClassId}/type/{type}")
  public ResponseEntity<FlightCabinAncillaryResponse> getFlightCabinAncillaryByFlightIdAndCabinClassIdAndType(
      @PathVariable Long flightId, @PathVariable Long cabinClassId, @PathVariable AncillaryType type) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(flightCabinAncillaryService
            .getByFlightIdAndCabinClassIdAndType(flightId, cabinClassId, type));
  }

  @PutMapping("/{id}")
  public ResponseEntity<FlightCabinAncillaryResponse> updateFlightCabinAncillary(
      @PathVariable Long id, @Valid @RequestBody FlightCabinAncillaryRequest flightCabinAncillaryRequest)
      throws Exception {
    return ResponseEntity.status(HttpStatus.OK)
        .body(flightCabinAncillaryService.updateFlightCabinAncillary(id, flightCabinAncillaryRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFlightCabinAncillary(@PathVariable Long id) throws Exception {
    flightCabinAncillaryService.deleteFlightCabinAncillary(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/price/total")
  public ResponseEntity<Double> calculateAncillaryPrice(@RequestBody List<Long> flightCabinAncillaryIds) {
    return ResponseEntity.ok(flightCabinAncillaryService.calculateAncillaryPrice(flightCabinAncillaryIds));
  }

}
