package com.thanhdoan.mapper;

import com.thanhdoan.model.Meal;
import com.thanhdoan.payload.request.MealRequest;
import com.thanhdoan.payload.response.MealResponse;

public class MealMapper {
  public static MealResponse toResponse(Meal meal) {
    return MealResponse.builder()
        .id(meal.getId())
        .code(meal.getCode())
        .name(meal.getName())
        .mealType(meal.getMealType())
        .dietaryRestriction(meal.getDietaryRestriction())
        .ingredients(meal.getIngredients())
        .imageUrl(meal.getImageUrl())
        .available(meal.getAvailable())
        .requiresAdvanceBooking(meal.getRequiresAdvanceBooking())
        .advanceBookingHours(meal.getAdvanceBookingHours())
        .displayOrder(meal.getDisplayOrder())
        .airlineId(meal.getAirlineId())
        .createdAt(meal.getCreatedAt())
        .updatedAt(meal.getUpdatedAt())
        .build();
  }

  public static Meal toEntity(Long airlineId, MealRequest mealRequest) {
    return Meal.builder()
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
  }
}
