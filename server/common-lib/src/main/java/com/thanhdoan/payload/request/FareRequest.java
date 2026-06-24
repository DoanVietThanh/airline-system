package com.thanhdoan.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FareRequest {
  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "RBD code is required")
  private String rbdCode;

  @NotNull(message = "Flight ID is required")
  private Long flightId;

  @NotNull(message = "Cabin class ID is required")
  private Long cabinClassId;

  @NotNull(message = "Base fare is required")
  @Positive
  private Double baseFare;

  private Double taxesAndFees;
  private Double airlineFees;
  private Double currentPrice;

  @Size(max = 100, message = "Fare label must be less than 100 characters")
  private String fareLabel;

  // Seat Benefits
  private Boolean extraSeatSpace;
  private Boolean preferredSeatChoice;
  private Boolean advanceSeatSelection;
  private Boolean guaranteedSeatTogether;

  // Boarding Benefits
  private Boolean priorityBoarding;
  private Boolean priorityCheckin;
  private Boolean fastTrackSecurity;

  // In-Flight Benefits
  private Boolean complimentaryMeals;
  private Boolean premiumMealChoice;
  private Boolean inFlightInternet;
  private Boolean inFlightEntertainment;
  private Boolean complimentaryBeverages;

  // Flexibility Benefits
  private Boolean freeDateChange;
  private Boolean partialRefund;
  private Boolean fullRefund;

  // Premium Service Benefits
  private Boolean loungeAccess;
  private Boolean airportTransfer;
}
