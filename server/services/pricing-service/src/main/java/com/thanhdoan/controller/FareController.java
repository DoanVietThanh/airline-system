package com.thanhdoan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.model.Fare;
import com.thanhdoan.service.FareService;

import jakarta.validation.Valid;

import com.thanhdoan.payload.request.FareRequest;
import com.thanhdoan.payload.response.FareResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fares")
public class FareController {

  private final FareService fareService;

  @PostMapping
  public ResponseEntity<FareResponse> createFare(@Valid @RequestBody FareRequest fareRequest) throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED).body(fareService.createFare(fareRequest));
  }

  @GetMapping
  public ResponseEntity<List<Fare>> getFares() throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(fareService.getFares());
  }

  @GetMapping("/{id}")
  public ResponseEntity<FareResponse> getFareById(@PathVariable Long id) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(fareService.getFareById(id));
  }

  @GetMapping("/flight/{flightId}/cabin-class/{cabinClassId}")
  public ResponseEntity<List<FareResponse>> getFaresByFlightIdAndCabinClass(@PathVariable Long flightId,
      @PathVariable Long cabinClassId) throws Exception {
    return ResponseEntity.status(HttpStatus.OK)
        .body(fareService.getFaresByFlightIdAndCabinCLassId(flightId, cabinClassId));
  }

  // getFaresByIds
  @GetMapping("/batch-by-ids")
  public ResponseEntity<Map<Long, FareResponse>> getFaresByIds(@PathVariable List<Long> ids) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(fareService.getFaresByIds(ids));
  }

  // getLowestFarePerFlight
  @GetMapping("/search")
  public ResponseEntity<Map<Long, FareResponse>> getLowestFarePerFlight(@RequestBody List<Long> flightIds,
      @RequestParam Long cabinClassId) throws Exception {
    Map<Long, FareResponse> fares = fareService.getLowestFarePerFlight(flightIds, cabinClassId);
    if (fares.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(fares);
  }

  @PutMapping("/{id}")
  public ResponseEntity<FareResponse> updateFare(@PathVariable Long id, @Valid @RequestBody FareRequest fareRequest)
      throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(fareService.updateFare(id, fareRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFare(@PathVariable Long id) throws Exception {
    fareService.deleteFare(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
