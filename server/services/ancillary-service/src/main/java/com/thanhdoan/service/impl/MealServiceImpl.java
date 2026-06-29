package com.thanhdoan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.thanhdoan.mapper.MealMapper;
import com.thanhdoan.model.Meal;
import com.thanhdoan.payload.request.MealRequest;
import com.thanhdoan.payload.response.MealResponse;
import com.thanhdoan.repository.MealRepository;
import com.thanhdoan.service.MealService;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {
  private final MealRepository mealRepository;

  @Override
  public MealResponse createMeal(Long airlineId, MealRequest mealRequest) throws Exception {
    if (mealRepository.existsByCodeAndAirlineId(mealRequest.getCode(), airlineId)) {
      throw new Exception("Meal with code " + mealRequest.getCode() + " already exists");
    }

    Meal meal = Meal.builder()
        .code(mealRequest.getCode())
        .name(mealRequest.getName())
        .mealType(mealRequest.getMealType())
        .dietaryRestriction(mealRequest.getDietaryRestriction())
        .ingredients(mealRequest.getIngredients())
        .imageUrl(mealRequest.getImageUrl())
        .requiresAdvanceBooking(
            mealRequest.getRequiresAdvanceBooking() != null ? mealRequest.getRequiresAdvanceBooking() : false)
        .advanceBookingHours(mealRequest.getAdvanceBookingHours())
        .displayOrder(mealRequest.getDisplayOrder() != null ? mealRequest.getDisplayOrder() : 0)
        .airlineId(airlineId)
        .build();

    Meal savedMeal = mealRepository.save(meal);

    return MealMapper.toResponse(savedMeal);
  }

  @Override
  public MealResponse getMealById(Long id) throws Exception {
    Meal meal = mealRepository.findById(id)
        .orElseThrow(() -> new Exception("Meal with id " + id + " not found"));

    return MealMapper.toResponse(meal);
  }

  @Override
  public MealResponse updateMeal(Long airlineId, Long id, MealRequest mealRequest) throws Exception {
    Meal meal = mealRepository.findById(id)
        .orElseThrow(() -> new Exception("Meal with id " + id + " not found"));

    if (mealRequest.getCode() != null
        && mealRepository.existsByAirlineIdAndCodeAndIdNot(airlineId, mealRequest.getCode(), meal.getId())) {
      throw new Exception("Meal with code " + mealRequest.getCode() + " already exists");
    }

    meal.setCode(mealRequest.getCode());
    meal.setName(mealRequest.getName());
    meal.setMealType(mealRequest.getMealType());
    meal.setDietaryRestriction(mealRequest.getDietaryRestriction());
    meal.setIngredients(mealRequest.getIngredients());
    meal.setImageUrl(mealRequest.getImageUrl());
    meal.setRequiresAdvanceBooking(mealRequest.getRequiresAdvanceBooking());
    meal.setAdvanceBookingHours(mealRequest.getAdvanceBookingHours());
    meal.setDisplayOrder(mealRequest.getDisplayOrder());
    Meal updatedMeal = mealRepository.save(meal);

    return MealMapper.toResponse(updatedMeal);
  }

  @Override
  public List<MealResponse> getByAirlineId(Long airlineId) {
    List<Meal> meals = mealRepository.findByAirlineId(airlineId);

    return meals.stream()
        .map(MealMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteMeal(Long id) throws Exception {
    Meal meal = mealRepository.findById(id)
        .orElseThrow(() -> new Exception("Meal with id " + id + " not found"));
    mealRepository.delete(meal);
  }

  @Override
  public MealResponse updateAvailable(Long id, Boolean available) throws Exception {
    Meal meal = mealRepository.findById(id)
        .orElseThrow(() -> new Exception("Meal with id " + id + " not found"));

    meal.setAvailable(available);
    mealRepository.save(meal);

    return MealMapper.toResponse(meal);
  }

}
