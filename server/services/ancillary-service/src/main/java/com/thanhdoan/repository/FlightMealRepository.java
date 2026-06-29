package com.thanhdoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhdoan.model.FlightMeal;

public interface FlightMealRepository extends JpaRepository<FlightMeal, Long> {

  List<FlightMeal> findByFlightId(Long flightId);

  boolean existsByFlightIdAndMealId(Long flightId, Long mealId);

  List<FlightMeal> findByMealIdIn(List<Long> mealIds);

}
