package com.thanhdoan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.request.FlightMealRequest;
import com.thanhdoan.payload.response.FlightMealResponse;
import com.thanhdoan.service.FlightMealService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flight-meals")
public class FlightMealController {
  private final FlightMealService flightMealService;

  @PostMapping
  public ResponseEntity<FlightMealResponse> createFlightMeal(@Valid @RequestBody FlightMealRequest flightMealRequest)
      throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED).body(flightMealService.createFlightMeal(flightMealRequest));
  }

  @PostMapping("/price/total")
  public ResponseEntity<Double> calculateMealPrice(@RequestBody List<Long> mealIds) {
    double price = flightMealService.calculateMealPrice(mealIds);
    return ResponseEntity.status(HttpStatus.OK).body(price);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FlightMealResponse> getFlightMealById(@PathVariable Long id) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(flightMealService.getFlightMealById(id));
  }

  @GetMapping("/flight/{flightId}")
  public ResponseEntity<List<FlightMealResponse>> getFlightMealsByFlightId(@PathVariable Long flightId) {
    return ResponseEntity.status(HttpStatus.OK).body(flightMealService.getFlightMealsByFlightId(flightId));
  }

  @GetMapping("/all")
  public ResponseEntity<List<FlightMealResponse>> getAllFlightMealsByIds(@RequestParam List<Long> ids) {
    return ResponseEntity.status(HttpStatus.OK).body(flightMealService.getAllFlightMealsByIds(ids));
  }

  @PutMapping("/{id}")
  public ResponseEntity<FlightMealResponse> updateFlightMeal(@PathVariable Long id,
      @RequestBody FlightMealRequest flightMealRequest) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(flightMealService.updateFlightMeal(id, flightMealRequest));
  }

  @PutMapping("/{id}/availability")
  public ResponseEntity<FlightMealResponse> updateFlightMealAvailability(@PathVariable Long id,
      @RequestParam Boolean available) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(flightMealService.updateFlightMealAvailability(id, available));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFlightMeal(@PathVariable Long id) throws Exception {
    flightMealService.deleteFlightMeal(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
