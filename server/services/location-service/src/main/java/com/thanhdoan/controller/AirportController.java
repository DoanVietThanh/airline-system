package com.thanhdoan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.response.AirportResponse;
import com.thanhdoan.payload.response.ApiResponse;
import com.thanhdoan.service.AirportService;

import com.thanhdoan.payload.request.AirportRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

  private final AirportService airportService;

  @PostMapping
  public ResponseEntity<AirportResponse> createAirport(@Valid @RequestBody AirportRequest request)
      throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED).body(airportService.createAirport(request));
  }

  // @PostMapping("/bulk")
  // public ResponseEntity<List<AirportResponse>> createBulkAirports(
  // @Valid @RequestBody List<AirportRequest> requests)
  // throws Exception {
  // return
  // ResponseEntity.status(HttpStatus.CREATED).body(airportService.createBulkAirports(requests));
  // }

  @GetMapping("/{id}")
  public ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id) throws Exception {
    return ResponseEntity.ok(airportService.getAirportById(id));
  }

  @GetMapping
  public ResponseEntity<List<AirportResponse>> getAllAirports() {
    return ResponseEntity.ok(airportService.getAllAirports());
  }

  @GetMapping("/city/{cityId}")
  public ResponseEntity<List<AirportResponse>> getAirportsByCityId(@PathVariable Long cityId) {
    return ResponseEntity.ok(airportService.getAirportsByCityId(cityId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AirportResponse> updateAirport(
      @PathVariable Long id,
      @Valid @RequestBody AirportRequest request) throws Exception {
    return ResponseEntity.ok(airportService.updateAirport(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteAirport(@PathVariable Long id) throws Exception {
    airportService.deleteAirport(id);
    return ResponseEntity.ok(new ApiResponse("Airport deleted successfully"));
  }
}
