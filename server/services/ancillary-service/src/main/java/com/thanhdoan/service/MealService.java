package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.payload.request.MealRequest;
import com.thanhdoan.payload.response.MealResponse;

public interface MealService {
  MealResponse createMeal(Long airlineId, MealRequest mealRequest) throws Exception;

  MealResponse getMealById(Long id) throws Exception;

  MealResponse updateMeal(Long airlineId, Long id, MealRequest mealRequest) throws Exception;

  List<MealResponse> getByAirlineId(Long airlineId);

  void deleteMeal(Long id) throws Exception;

  MealResponse updateAvailable(Long id, Boolean available) throws Exception;

}
