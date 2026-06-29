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

import com.thanhdoan.payload.request.AncillaryRequest;
import com.thanhdoan.payload.response.AncillaryResponse;
import com.thanhdoan.service.AncillaryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ancillaries")
public class AncillaryController {
  private final AncillaryService ancillaryService;

  @PostMapping
  public ResponseEntity<AncillaryResponse> createAncillary(@RequestHeader("X-Airline-Id") Long airlineId,
      @Valid @RequestBody AncillaryRequest ancillaryRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ancillaryService.createAncillary(airlineId, ancillaryRequest));
  }

  @GetMapping("/{id}")
  public ResponseEntity<AncillaryResponse> getAncillaryById(@PathVariable Long id) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(ancillaryService.getAncillaryById(id));
  }

  @GetMapping
  public ResponseEntity<List<AncillaryResponse>> getAncillariesByAirlineId(
      @RequestHeader("X-Airline-Id") Long airlineId) {
    return ResponseEntity.status(HttpStatus.OK).body(ancillaryService.getAncillariesByAirlineId(airlineId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AncillaryResponse> updateAncillary(@PathVariable Long id,
      @Valid @RequestBody AncillaryRequest ancillaryRequest) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(ancillaryService.updateAncillary(id, ancillaryRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAncillary(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
