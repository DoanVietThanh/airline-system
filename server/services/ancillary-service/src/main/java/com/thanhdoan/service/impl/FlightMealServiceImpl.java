package com.thanhdoan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanhdoan.mapper.FlightMealMapper;
import com.thanhdoan.model.FlightMeal;
import com.thanhdoan.model.Meal;
import com.thanhdoan.payload.request.FlightMealRequest;
import com.thanhdoan.payload.response.FlightMealResponse;
import com.thanhdoan.repository.FlightMealRepository;
import com.thanhdoan.repository.MealRepository;
import com.thanhdoan.service.FlightMealService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightMealServiceImpl implements FlightMealService {
  private final FlightMealRepository flightMealRepository;
  private final MealRepository mealRepository;

  @Override
  public FlightMealResponse createFlightMeal(FlightMealRequest flightMealRequest) throws Exception {
    Meal meal = mealRepository.findById(flightMealRequest.getMealId())
        .orElseThrow(() -> new Exception("Meal not found"));

    if (flightMealRepository.existsByFlightIdAndMealId(flightMealRequest.getFlightId(),
        flightMealRequest.getMealId())) {
      throw new Exception("Flight meal already exists");
    }

    FlightMeal flightMeal = FlightMeal.builder()
        .flightId(flightMealRequest.getFlightId())
        .meal(meal)
        .available(flightMealRequest.getAvailable())
        .price(flightMealRequest.getPrice())
        .displayOrder(flightMealRequest.getDisplayOrder())
        .build();
    FlightMeal savedFlightMeal = flightMealRepository.save(flightMeal);
    return FlightMealMapper.toResponse(savedFlightMeal);
  }

  @Override
  public FlightMealResponse getFlightMealById(Long id) throws Exception {
    FlightMeal flightMeal = flightMealRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight meal not found"));
    return FlightMealMapper.toResponse(flightMeal);
  }

  @Override
  public List<FlightMealResponse> getFlightMealsByFlightId(Long flightId) {
    List<FlightMeal> flightMeals = flightMealRepository.findByFlightId(flightId);
    return flightMeals.stream()
        .map(FlightMealMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<FlightMealResponse> getAllFlightMealsByIds(List<Long> ids) {
    List<FlightMeal> flightMeals = flightMealRepository.findAllById(ids);
    return flightMeals.stream()
        .map(FlightMealMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public FlightMealResponse updateFlightMeal(Long id, FlightMealRequest flightMealRequest) throws Exception {
    FlightMeal flightMeal = flightMealRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight meal not found"));

    flightMeal.setFlightId(flightMealRequest.getFlightId());

    if (flightMealRequest.getMealId() != null) {
      Meal meal = mealRepository.findById(flightMealRequest.getMealId())
          .orElseThrow(() -> new Exception("Meal not found"));
      flightMeal.setMeal(meal);
    }

    flightMeal.setAvailable(flightMealRequest.getAvailable());
    flightMeal.setPrice(flightMealRequest.getPrice());
    flightMeal.setDisplayOrder(flightMealRequest.getDisplayOrder());
    FlightMeal updatedFlightMeal = flightMealRepository.save(flightMeal);
    return FlightMealMapper.toResponse(updatedFlightMeal);
  }

  @Override
  public FlightMealResponse updateFlightMealAvailability(Long id, Boolean available) throws Exception {
    FlightMeal flightMeal = flightMealRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight meal not found"));
    flightMeal.setAvailable(available);
    FlightMeal updatedFlightMeal = flightMealRepository.save(flightMeal);
    return FlightMealMapper.toResponse(updatedFlightMeal);
  }

  @Override
  public void deleteFlightMeal(Long id) throws Exception {
    FlightMeal flightMeal = flightMealRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight meal not found"));
    flightMealRepository.delete(flightMeal);
  }

  @Override
  public Double calculateMealPrice(List<Long> mealIds) {
    double price = 0.0;
    List<FlightMeal> flightMeals = flightMealRepository.findByMealIdIn(mealIds);
    for (FlightMeal flightMeal : flightMeals) {
      price += flightMeal.getPrice();
    }
    return price;
  }

}
