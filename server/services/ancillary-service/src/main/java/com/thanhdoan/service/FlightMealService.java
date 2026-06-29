package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.payload.request.FlightMealRequest;
import com.thanhdoan.payload.response.FlightMealResponse;

public interface FlightMealService {
  FlightMealResponse createFlightMeal(FlightMealRequest flightMealRequest) throws Exception;

  FlightMealResponse getFlightMealById(Long id) throws Exception;

  List<FlightMealResponse> getFlightMealsByFlightId(Long flightId);

  List<FlightMealResponse> getAllFlightMealsByIds(List<Long> ids);

  FlightMealResponse updateFlightMeal(Long id, FlightMealRequest flightMealRequest) throws Exception;

  FlightMealResponse updateFlightMealAvailability(Long id, Boolean available) throws Exception;

  void deleteFlightMeal(Long id) throws Exception;

  Double calculateMealPrice(List<Long> mealIds);

}
