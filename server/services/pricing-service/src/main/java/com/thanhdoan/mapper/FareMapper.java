package com.thanhdoan.mapper;

import com.thanhdoan.embeddable.SeatBenefits;
import com.thanhdoan.embeddable.BoardingBenefits;
import com.thanhdoan.embeddable.FlexibilityBenefits;
import com.thanhdoan.embeddable.InFlightBenefits;
import com.thanhdoan.embeddable.PremiumServiceBenefits;
import com.thanhdoan.model.Fare;
import com.thanhdoan.payload.request.FareRequest;
import com.thanhdoan.payload.response.FareResponse;

public class FareMapper {
  // toEntity, toResponse

  public static Fare toEntity(FareRequest fareRequest) {
    if (fareRequest == null) {
      return null;
    }

    Double calculatedPrice = fareRequest.getCurrentPrice();
    if (calculatedPrice == null) {
      calculatedPrice = fareRequest.getBaseFare() + fareRequest.getTaxesAndFees() + fareRequest.getAirlineFees();
    }

    SeatBenefits seatBenefits = SeatBenefits.builder()
        .extraSeatSpace(bool(fareRequest.getExtraSeatSpace()))
        .preferredSeatChoice(bool(fareRequest.getPreferredSeatChoice()))
        .advanceSeatSelection(bool(fareRequest.getAdvanceSeatSelection()))
        .guaranteedSeatTogether(bool(fareRequest.getGuaranteedSeatTogether()))
        .build();

    BoardingBenefits boardingBenefits = BoardingBenefits.builder()
        .priorityBoarding(bool(fareRequest.getPriorityBoarding()))
        .priorityCheckin(bool(fareRequest.getPriorityCheckin()))
        .fastTrackSecurity(bool(fareRequest.getFastTrackSecurity()))
        .build();

    InFlightBenefits inFlightBenefits = InFlightBenefits.builder()
        .complimentaryMeals(fareRequest.getComplimentaryMeals())
        .premiumMealChoice(fareRequest.getPremiumMealChoice())
        .inFlightInternet(fareRequest.getInFlightInternet())
        .inFlightEntertainment(bool(fareRequest.getInFlightEntertainment()))
        .complimentaryBeverages(bool(fareRequest.getComplimentaryBeverages()))
        .build();

    FlexibilityBenefits flexibilityBenefits = FlexibilityBenefits.builder()
        .freeDateChange(bool(fareRequest.getFreeDateChange()))
        .partialRefund(bool(fareRequest.getPartialRefund()))
        .fullRefund(bool(fareRequest.getFullRefund()))
        .build();

    PremiumServiceBenefits premiumServiceBenefits = PremiumServiceBenefits.builder()
        .loungeAccess(bool(fareRequest.getLoungeAccess()))
        .airportTransfer(bool(fareRequest.getAirportTransfer()))
        .build();

    return Fare.builder()
        .name(fareRequest.getName())
        .rbdCode(fareRequest.getRbdCode())
        .flightId(fareRequest.getFlightId())
        .cabinClassId(fareRequest.getCabinClassId())
        .baseFare(fareRequest.getBaseFare())
        .taxesAndFees(fareRequest.getTaxesAndFees())
        .airlineFees(fareRequest.getAirlineFees())
        .currentPrice(calculatedPrice)
        .fareLabel(fareRequest.getFareLabel())
        .seatBenefits(seatBenefits)
        .boardingBenefits(boardingBenefits)
        .inFlightBenefits(inFlightBenefits)
        .flexibilityBenefits(flexibilityBenefits)
        .premiumServiceBenefits(premiumServiceBenefits)
        .build();
  }

  public static FareResponse toResponse(Fare fare) {
    if (fare == null) {
      return null;
    }
    return FareResponse.builder()
        .id(fare.getId())
        .name(fare.getName())
        .rbdCode(fare.getRbdCode())
        .flightId(fare.getFlightId())
        .cabinClassId(fare.getCabinClassId())
        .cabinClass(fare.getCabinClass())
        .baseFare(fare.getBaseFare())
        .taxesAndFees(fare.getTaxesAndFees())
        .airlineFees(fare.getAirlineFees())
        .currentPrice(fare.getCurrentPrice())
        .totalPrice(fare.getCurrentPrice())
        .fareLabel(fare.getFareLabel())
        .fareRulesId(fare.getFareRules() != null ? fare.getFareRules().getId() : null)

        // Seat Benefits
        .extraSeatSpace(fare.getSeatBenefits() != null ? fare.getSeatBenefits().getExtraSeatSpace() : false)
        .preferredSeatChoice(fare.getSeatBenefits() != null ? fare.getSeatBenefits().getPreferredSeatChoice() : false)
        .advanceSeatSelection(fare.getSeatBenefits() != null ? fare.getSeatBenefits().getAdvanceSeatSelection() : false)
        .guaranteedSeatTogether(
            fare.getSeatBenefits() != null ? fare.getSeatBenefits().getGuaranteedSeatTogether() : false)

        // Boarding Benefits
        .priorityBoarding(fare.getBoardingBenefits() != null ? fare.getBoardingBenefits().getPriorityBoarding() : false)
        .priorityCheckin(fare.getBoardingBenefits() != null ? fare.getBoardingBenefits().getPriorityCheckin() : false)
        .fastTrackSecurity(
            fare.getBoardingBenefits() != null ? fare.getBoardingBenefits().getFastTrackSecurity() : false)

        // In-Flight Benefits
        .complimentaryMeals(
            fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getComplimentaryMeals() : false)
        .premiumMealChoice(
            fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getPremiumMealChoice() : false)
        .inFlightInternet(fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getInFlightInternet() : false)
        .inFlightEntertainment(
            fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getInFlightEntertainment() : false)
        .complimentaryBeverages(
            fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getComplimentaryBeverages() : false)

        // Flexibility Benefits
        .freeDateChange(
            fare.getFlexibilityBenefits() != null ? fare.getFlexibilityBenefits().getFreeDateChange() : false)
        .partialRefund(fare.getFlexibilityBenefits() != null ? fare.getFlexibilityBenefits().getPartialRefund() : false)
        .fullRefund(fare.getFlexibilityBenefits() != null ? fare.getFlexibilityBenefits().getFullRefund() : false)

        // Premium Service Benefits
        .loungeAccess(
            fare.getPremiumServiceBenefits() != null ? fare.getPremiumServiceBenefits().getLoungeAccess() : false)
        .airportTransfer(
            fare.getPremiumServiceBenefits() != null ? fare.getPremiumServiceBenefits().getAirportTransfer() : false)

        // Nested response
        .fareRules(fare.getFareRules() != null ? FareRulesMapper.toResponse(fare.getFareRules()) : null)
        .baggagePolicy(fare.getBaggagePolicy() != null ? BaggagePolicyMapper.toResponse(fare.getBaggagePolicy()) : null)
        .createdAt(fare.getCreatedAt())
        .updatedAt(fare.getUpdatedAt())
        .build();
  }

  // updateEnity
  public static Fare updateEntity(FareRequest fareRequest, Fare fare) {
    if (fare == null || fareRequest == null) {
      return null;
    }
    if (fareRequest.getName() != null) {
      fare.setName(fareRequest.getName());
    }
    if (fareRequest.getRbdCode() != null) {
      fare.setRbdCode(fareRequest.getRbdCode());
    }
    if (fareRequest.getFlightId() != null) {
      fare.setFlightId(fareRequest.getFlightId());
    }
    if (fareRequest.getCabinClassId() != null) {
      fare.setCabinClassId(fareRequest.getCabinClassId());
    }
    if (fareRequest.getBaseFare() != null) {
      fare.setBaseFare(fareRequest.getBaseFare());
    }
    if (fareRequest.getTaxesAndFees() != null) {
      fare.setTaxesAndFees(fareRequest.getTaxesAndFees());
    }
    if (fareRequest.getAirlineFees() != null) {
      fare.setAirlineFees(fareRequest.getAirlineFees());
    }
    if (fareRequest.getCurrentPrice() != null) {
      fare.setCurrentPrice(fareRequest.getCurrentPrice());
    }
    if (fareRequest.getFareLabel() != null) {
      fare.setFareLabel(fareRequest.getFareLabel());
    }

    // Seat Benefits
    SeatBenefits seatBenefits = fare.getSeatBenefits();
    if (fareRequest.getExtraSeatSpace() != null) {
      seatBenefits.setExtraSeatSpace(fareRequest.getExtraSeatSpace());
    }
    if (fareRequest.getPreferredSeatChoice() != null) {
      seatBenefits.setPreferredSeatChoice(fareRequest.getPreferredSeatChoice());
    }
    if (fareRequest.getAdvanceSeatSelection() != null) {
      seatBenefits.setAdvanceSeatSelection(fareRequest.getAdvanceSeatSelection());
    }
    if (fareRequest.getGuaranteedSeatTogether() != null) {
      seatBenefits.setGuaranteedSeatTogether(fareRequest.getGuaranteedSeatTogether());
    }
    fare.setSeatBenefits(seatBenefits);

    // Boarding Benefits
    BoardingBenefits boardingBenefits = fare.getBoardingBenefits();
    if (fareRequest.getPriorityBoarding() != null) {
      boardingBenefits.setPriorityBoarding(fareRequest.getPriorityBoarding());
    }
    if (fareRequest.getPriorityCheckin() != null) {
      boardingBenefits.setPriorityCheckin(fareRequest.getPriorityCheckin());
    }
    if (fareRequest.getFastTrackSecurity() != null) {
      boardingBenefits.setFastTrackSecurity(fareRequest.getFastTrackSecurity());
    }
    fare.setBoardingBenefits(boardingBenefits);

    // In-Flight Benefits
    InFlightBenefits inFlightBenefits = fare.getInFlightBenefits();
    if (fareRequest.getComplimentaryMeals() != null) {
      inFlightBenefits.setComplimentaryMeals(fareRequest.getComplimentaryMeals());
    }
    if (fareRequest.getPremiumMealChoice() != null) {
      inFlightBenefits.setPremiumMealChoice(fareRequest.getPremiumMealChoice());
    }
    if (fareRequest.getInFlightInternet() != null) {
      inFlightBenefits.setInFlightInternet(fareRequest.getInFlightInternet());
    }
    if (fareRequest.getInFlightEntertainment() != null) {
      inFlightBenefits.setInFlightEntertainment(fareRequest.getInFlightEntertainment());
    }
    if (fareRequest.getComplimentaryBeverages() != null) {
      inFlightBenefits.setComplimentaryBeverages(fareRequest.getComplimentaryBeverages());
    }
    fare.setInFlightBenefits(inFlightBenefits);

    // Flexibility Benefits
    FlexibilityBenefits flexibilityBenefits = fare.getFlexibilityBenefits();
    if (fareRequest.getFreeDateChange() != null) {
      flexibilityBenefits.setFreeDateChange(fareRequest.getFreeDateChange());
    }
    if (fareRequest.getPartialRefund() != null) {
      flexibilityBenefits.setPartialRefund(fareRequest.getPartialRefund());
    }
    if (fareRequest.getFullRefund() != null) {
      flexibilityBenefits.setFullRefund(fareRequest.getFullRefund());
    }
    fare.setFlexibilityBenefits(flexibilityBenefits);

    // Premium Service Benefits
    PremiumServiceBenefits premiumServiceBenefits = fare.getPremiumServiceBenefits();
    if (fareRequest.getLoungeAccess() != null) {
      premiumServiceBenefits.setLoungeAccess(fareRequest.getLoungeAccess());
    }
    if (fareRequest.getAirportTransfer() != null) {
      premiumServiceBenefits.setAirportTransfer(fareRequest.getAirportTransfer());
    }
    fare.setPremiumServiceBenefits(premiumServiceBenefits);

    return fare;
  }

  private static boolean bool(Boolean value) {
    return value != null ? value : false;
  }
}
