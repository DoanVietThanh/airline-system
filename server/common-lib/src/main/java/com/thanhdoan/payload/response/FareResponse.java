package com.thanhdoan.payload.response;

import java.time.Instant;

import com.thanhdoan.enums.CabinClassType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FareResponse {

  private Long id;
  private String name;
  private String rbdCode;
  private Long flightId;
  private Long cabinClassId;
  private CabinClassType cabinClass;

  // Pricing
  private Double baseFare;
  private Double taxesAndFees;
  private Double airlineFees;
  private Double currentPrice;
  private Double totalPrice;
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

  // Relationships
  private Long fareRulesId;
  private FareRulesResponse fareRules;
  private BaggagePolicyResponse baggagePolicy;

  // Audit Timestamps
  private Instant createdAt;
  private Instant updatedAt;
}
