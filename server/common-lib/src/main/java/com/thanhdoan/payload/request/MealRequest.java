package com.thanhdoan.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealRequest {

  @NotBlank(message = "Code is required")
  private String code;

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Meal type is required")
  @Size(max = 50, message = "Meal type must be less than 50 characters")
  private String mealType;

  @Size(max = 100, message = "Dietary restriction must be less than 100 characters")
  private String dietaryRestriction;

  @Size(max = 2000, message = "Ingredients must be less than 2000 characters")
  private String ingredients;

  @Size(max = 500, message = "Image URL must be less than 500 characters")
  private String imageUrl;

  private Boolean requiresAdvanceBooking;

  private Integer advanceBookingHours;

  private Integer displayOrder;

}
