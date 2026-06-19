package com.thanhdoan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.thanhdoan.service.AirlineService;

import jakarta.validation.Valid;

import com.thanhdoan.enums.AirlineStatus;
import com.thanhdoan.payload.request.AirlineRequest;
import com.thanhdoan.payload.response.AirlineDropdownItem;
import com.thanhdoan.payload.response.AirlineResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {

  private final AirlineService airlineService;

  // ---------- CRUD ----------

  @PostMapping
  public ResponseEntity<AirlineResponse> createAirline(
      @Valid @RequestBody AirlineRequest request,
      @RequestHeader("X-User-Id") Long userId) {
    return ResponseEntity.ok(airlineService.createAirline(request, userId));
  }

  @GetMapping("/admin")
  public ResponseEntity<AirlineResponse> getAirlineByOwner(
      @RequestHeader("X-User-Id") Long userId) throws Exception {
    return ResponseEntity.ok(airlineService.getAirlineByOwner(userId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<AirlineResponse> getAirlineById(

      @PathVariable Long id) throws Exception {
    return ResponseEntity.ok(airlineService.getAirlineById(id));
  }

  @GetMapping
  public ResponseEntity<Page<AirlineResponse>> getAllAirlines(Pageable pageable) {
    return ResponseEntity.ok(airlineService.getAllAirlines(pageable));
  }

  @GetMapping("/dropdown")
  public ResponseEntity<List<AirlineDropdownItem>> getAirlinesForDropdown() {
    return ResponseEntity.ok(airlineService.getAirlinesForDropdown());
  }

  @PutMapping
  public ResponseEntity<AirlineResponse> updateAirline(
      @Valid @RequestBody AirlineRequest request,
      @RequestHeader("X-User-Id") Long userId) throws Exception {
    return ResponseEntity.ok(airlineService.updateAirline(request, userId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAirline(
      @PathVariable Long id,
      @RequestHeader("X-User-Id") Long userId) throws Exception {
    airlineService.deleteAirline(id, userId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{id}/approve")
  public ResponseEntity<AirlineResponse> approveAirline(@PathVariable Long id) throws Exception {
    return ResponseEntity.ok(airlineService.changeStatusByAdmin(id, AirlineStatus.ACTIVE));
  }

  @PostMapping("/{id}/suspend")
  public ResponseEntity<AirlineResponse> suspendAirline(@PathVariable Long id) throws Exception {
    return ResponseEntity.ok(airlineService.changeStatusByAdmin(id, AirlineStatus.INACTIVE));
  }

  @PostMapping("/{id}/ban")
  public ResponseEntity<AirlineResponse> banAirline(@PathVariable Long id) throws Exception {
    return ResponseEntity.ok(airlineService.changeStatusByAdmin(id, AirlineStatus.BANNED));
  }

}
