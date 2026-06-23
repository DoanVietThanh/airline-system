package com.thanhdoan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.request.AircraftRequest;
import com.thanhdoan.payload.response.AircraftResponse;
import com.thanhdoan.service.AircraftService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/aircrafts")
@RequiredArgsConstructor
public class AircraftController {

  private final AircraftService aircraftService;

  @PostMapping
  public ResponseEntity<AircraftResponse> createAircraft(
      @RequestBody AircraftRequest request,
      @RequestHeader("X-User-Id") Long userId) throws Exception {
    return ResponseEntity.ok(aircraftService.createAircraft(request, userId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<AircraftResponse> getAircraftById(@PathVariable Long id)
      throws Exception {
    return ResponseEntity.ok(aircraftService.getAircraftById(id));
  }

  @GetMapping
  public ResponseEntity<List<AircraftResponse>> listAllAircrafts(
      @RequestHeader("X-User-Id") Long userId) throws Exception {
    return ResponseEntity.ok(aircraftService.listAllAircraftsByOwner(userId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AircraftResponse> updateAircraft(
      @PathVariable Long id,
      @RequestBody AircraftRequest request,
      @RequestHeader("X-User-Id") Long userId) throws Exception {
    return ResponseEntity.ok(aircraftService.updateAircraft(id, request, userId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAircraft(@PathVariable Long id)
      throws Exception {
    aircraftService.deleteAircraft(id);
    return ResponseEntity.noContent().build();
  }
}
