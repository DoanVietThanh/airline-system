package com.thanhdoan.mapper;

import com.thanhdoan.model.Fare;
import com.thanhdoan.model.FareRules;
import com.thanhdoan.payload.request.FareRulesRequest;
import com.thanhdoan.payload.response.FareRulesResponse;

public class FareRulesMapper {
  // toEntity
  public static FareRules toEntity(FareRulesRequest fareRulesRequest, Fare fare) {
    return FareRules.builder()
        .ruleName(fareRulesRequest.getRuleName())
        .fare(fare)
        .airlineId(fareRulesRequest.getAirlineId())
        .isRefundable(fareRulesRequest.getIsRefundable())
        .changeFee(fareRulesRequest.getChangeFee())
        .cancellationFee(fareRulesRequest.getCancellationFee())
        .refundDeadlineDays(fareRulesRequest.getRefundDeadlineDays())
        .changeDeadlineHours(fareRulesRequest.getChangeDeadlineHours())
        .isChangeable(fareRulesRequest.getIsChangeable() != null ? fareRulesRequest.getIsChangeable() : false)
        .build();
  }

  public static FareRulesResponse toResponse(FareRules fareRules) {
    if (fareRules == null) {
      return null;
    }
    return FareRulesResponse.builder()
        .id(fareRules.getId())
        .ruleName(fareRules.getRuleName())
        .fareId(fareRules.getFare().getId())
        .airlineId(fareRules.getAirlineId())
        .isRefundable(fareRules.getIsRefundable())
        .changeFee(fareRules.getChangeFee())
        .cancellationFee(fareRules.getCancellationFee())
        .refundDeadlineDays(fareRules.getRefundDeadlineDays())
        .changeDeadlineHours(fareRules.getChangeDeadlineHours())
        .isChangeable(fareRules.getIsChangeable())
        .createdAt(fareRules.getCreatedAt())
        .updatedAt(fareRules.getUpdatedAt())
        .build();
  }

  public static void updateEntity(FareRulesRequest fareRulesRequest, FareRules fareRules) {
    if (fareRulesRequest == null) {
      return;
    }
    if (fareRulesRequest.getRuleName() != null) {
      fareRules.setRuleName(fareRulesRequest.getRuleName());
    }
    if (fareRulesRequest.getAirlineId() != null) {
      fareRules.setAirlineId(fareRulesRequest.getAirlineId());
    }
    if (fareRulesRequest.getIsRefundable() != null) {
      fareRules.setIsRefundable(fareRulesRequest.getIsRefundable());
    }
    if (fareRulesRequest.getChangeFee() != null) {
      fareRules.setChangeFee(fareRulesRequest.getChangeFee());
    }
    if (fareRulesRequest.getCancellationFee() != null) {
      fareRules.setCancellationFee(fareRulesRequest.getCancellationFee());
    }
    if (fareRulesRequest.getRefundDeadlineDays() != null) {
      fareRules.setRefundDeadlineDays(fareRulesRequest.getRefundDeadlineDays());
    }
    if (fareRulesRequest.getChangeDeadlineHours() != null) {
      fareRules.setChangeDeadlineHours(fareRulesRequest.getChangeDeadlineHours());
    }
    if (fareRulesRequest.getIsChangeable() != null) {
      fareRules.setIsChangeable(fareRulesRequest.getIsChangeable());
    }
  }
}
