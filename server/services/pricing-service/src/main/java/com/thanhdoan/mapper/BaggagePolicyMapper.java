package com.thanhdoan.mapper;

import com.thanhdoan.model.BaggagePolicy;
import com.thanhdoan.model.Fare;
import com.thanhdoan.payload.request.BaggagePolicyRequest;
import com.thanhdoan.payload.response.BaggagePolicyResponse;

public class BaggagePolicyMapper {

  public static BaggagePolicy toEntity(BaggagePolicyRequest baggagePolicyRequest, Fare fare) {
    if (baggagePolicyRequest == null) {
      return null;
    }
    return BaggagePolicy.builder()
        .name(baggagePolicyRequest.getName())
        .fare(fare)
        .description(baggagePolicyRequest.getDescription())
        .cabinBaggageMaxWeight(baggagePolicyRequest.getCabinBaggageMaxWeight())
        .cabinBaggagePieces(
            baggagePolicyRequest.getCabinBaggagePieces() != null ? baggagePolicyRequest.getCabinBaggagePieces() : 0)
        .cabinBaggageWeightPerPiece(baggagePolicyRequest.getCabinBaggageWeightPerPiece())
        .cabinBaggageMaxDimension(baggagePolicyRequest.getCabinBaggageMaxDimension())
        .checkInBaggageMaxWeight(baggagePolicyRequest.getCheckInBaggageMaxWeight())
        .checkInBaggagePieces(
            baggagePolicyRequest.getCheckInBaggagePieces() != null ? baggagePolicyRequest.getCheckInBaggagePieces() : 0)
        .checkInBaggageWeightPerPiece(baggagePolicyRequest.getCheckInBaggageWeightPerPiece())
        .freeCheckedBagsAllowance(baggagePolicyRequest.getFreeCheckedBagsAllowance() != null
            ? baggagePolicyRequest.getFreeCheckedBagsAllowance()
            : 0)
        .priorityBaggage(
            baggagePolicyRequest.getPriorityBaggage() != null ? baggagePolicyRequest.getPriorityBaggage() : false)
        .extraBaggageAllowance(
            baggagePolicyRequest.getExtraBaggageAllowance() != null ? baggagePolicyRequest.getExtraBaggageAllowance()
                : false)
        .airlineId(baggagePolicyRequest.getAirlineId())
        .build();
  }

  public static BaggagePolicyResponse toResponse(BaggagePolicy baggagePolicy) {
    if (baggagePolicy == null) {
      return null;
    }
    return BaggagePolicyResponse.builder()
        .id(baggagePolicy.getId())
        .name(baggagePolicy.getName())
        .description(baggagePolicy.getDescription())
        .cabinBaggageMaxWeight(baggagePolicy.getCabinBaggageMaxWeight())
        .cabinBaggagePieces(baggagePolicy.getCabinBaggagePieces())
        .cabinBaggageWeightPerPiece(baggagePolicy.getCabinBaggageWeightPerPiece())
        .cabinBaggageMaxDimension(baggagePolicy.getCabinBaggageMaxDimension())
        .checkInBaggageMaxWeight(baggagePolicy.getCheckInBaggageMaxWeight())
        .checkInBaggagePieces(baggagePolicy.getCheckInBaggagePieces())
        .checkInBaggageWeightPerPiece(baggagePolicy.getCheckInBaggageWeightPerPiece())
        .freeCheckedBagsAllowance(baggagePolicy.getFreeCheckedBagsAllowance())
        .priorityBaggage(baggagePolicy.getPriorityBaggage())
        .extraBaggageAllowance(baggagePolicy.getExtraBaggageAllowance())
        .airlineId(baggagePolicy.getAirlineId())
        .fareId(baggagePolicy.getFare() != null ? baggagePolicy.getFare().getId() : null)
        .build();
  }

  public static void toUpdateEntity(BaggagePolicyRequest baggagePolicyRequest, BaggagePolicy baggagePolicy) {
    if (baggagePolicyRequest == null) {
      return;
    }
    if (baggagePolicyRequest.getName() != null) {
      baggagePolicy.setName(baggagePolicyRequest.getName());
    }
    if (baggagePolicyRequest.getDescription() != null) {
      baggagePolicy.setDescription(baggagePolicyRequest.getDescription());
    }
    if (baggagePolicyRequest.getCabinBaggageMaxWeight() != null) {
      baggagePolicy.setCabinBaggageMaxWeight(baggagePolicyRequest.getCabinBaggageMaxWeight());
    }
    if (baggagePolicyRequest.getCabinBaggagePieces() != null) {
      baggagePolicy.setCabinBaggagePieces(baggagePolicyRequest.getCabinBaggagePieces());
    }
    if (baggagePolicyRequest.getCabinBaggageWeightPerPiece() != null) {
      baggagePolicy.setCabinBaggageWeightPerPiece(baggagePolicyRequest.getCabinBaggageWeightPerPiece());
    }
    if (baggagePolicyRequest.getCabinBaggageMaxDimension() != null) {
      baggagePolicy.setCabinBaggageMaxDimension(baggagePolicyRequest.getCabinBaggageMaxDimension());
    }
    if (baggagePolicyRequest.getCheckInBaggageMaxWeight() != null) {
      baggagePolicy.setCheckInBaggageMaxWeight(baggagePolicyRequest.getCheckInBaggageMaxWeight());
    }
    if (baggagePolicyRequest.getCheckInBaggagePieces() != null) {
      baggagePolicy.setCheckInBaggagePieces(baggagePolicyRequest.getCheckInBaggagePieces());
    }
    if (baggagePolicyRequest.getCheckInBaggageWeightPerPiece() != null) {
      baggagePolicy.setCheckInBaggageWeightPerPiece(baggagePolicyRequest.getCheckInBaggageWeightPerPiece());
    }
    if (baggagePolicyRequest.getFreeCheckedBagsAllowance() != null) {
      baggagePolicy.setFreeCheckedBagsAllowance(baggagePolicyRequest.getFreeCheckedBagsAllowance());
    }
    if (baggagePolicyRequest.getPriorityBaggage() != null) {
      baggagePolicy.setPriorityBaggage(baggagePolicyRequest.getPriorityBaggage());
    }
    if (baggagePolicyRequest.getExtraBaggageAllowance() != null) {
      baggagePolicy.setExtraBaggageAllowance(baggagePolicyRequest.getExtraBaggageAllowance());
    }

  }
}
