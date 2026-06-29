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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.request.MealRequest;
import com.thanhdoan.payload.response.MealResponse;
import com.thanhdoan.service.MealService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meals")
public class MealController {

  private final MealService mealService;

  @PostMapping
  public ResponseEntity<MealResponse> createMeal(@RequestHeader("X-Airline-Id") Long airlineId,
      @Valid @RequestBody MealRequest mealRequest) throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED).body(mealService.createMeal(airlineId, mealRequest));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MealResponse> getMealById(@PathVariable Long id)
      throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(mealService.getMealById(id));
  }

  @GetMapping("/airline")
  public ResponseEntity<List<MealResponse>> getMealsByAirlineId(@RequestHeader("X-Airline-Id") Long airlineId)
      throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(mealService.getByAirlineId(airlineId));
  }

  @PutMapping("/{id}/availability")
  public ResponseEntity<MealResponse> updateAvailable(@PathVariable Long id, @RequestParam Boolean available)
      throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(mealService.updateAvailable(id, available));
  }

  @PutMapping("/{id}")
  public ResponseEntity<MealResponse> updateMeal(@RequestHeader("X-Airline-Id") Long airlineId, @PathVariable Long id,
      @Valid @RequestBody MealRequest mealRequest) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(mealService.updateMeal(airlineId, id, mealRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMeal(@PathVariable Long id)
      throws Exception {
    mealService.deleteMeal(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}