package com.thanhdoan.payload.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightMealRequest {

  @NotNull(message = "Flight ID is required")
  private Long flightId;

  @NotNull(message = "Meal ID is required")
  private Long mealId;

  private Boolean available;

  @DecimalMin(value = "0.0", message = "Price must be greater than 0")
  private Double price;

  @Min(value = 0, message = "Display order must be greater than 0")
  private Integer displayOrder;

  private String notes;
}
