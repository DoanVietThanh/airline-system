package com.thanhdoan.mapper;

import com.thanhdoan.model.Ancillary;
import com.thanhdoan.model.InsuranceCoverage;
import com.thanhdoan.payload.request.InsuranceCoverageRequest;
import com.thanhdoan.payload.response.InsuranceCoverageResponse;

public class InsuranceCoverageMapper {

  public static InsuranceCoverage toEntity(InsuranceCoverageRequest insuranceCoverageRequest, Ancillary ancillary) {
    if (insuranceCoverageRequest == null) {
      return null;
    }
    return InsuranceCoverage.builder()
        .ancillary(ancillary)
        .coverageType(insuranceCoverageRequest.getCoverageType())
        .name(insuranceCoverageRequest.getName())
        .description(insuranceCoverageRequest.getDescription())
        .coverageAmount(insuranceCoverageRequest.getCoverageAmount())
        .isFlat(insuranceCoverageRequest.getIsFlat())
        .claimCondition(insuranceCoverageRequest.getClaimCondition())
        .emergencyContact(insuranceCoverageRequest.getEmergencyContact())
        .displayOrder(insuranceCoverageRequest.getDisplayOrder())
        .active(insuranceCoverageRequest.getActive() != null ? insuranceCoverageRequest.getActive() : true)
        .build();
  }

  public static InsuranceCoverageResponse toResponse(InsuranceCoverage insuranceCoverage) {
    if (insuranceCoverage == null) {
      return null;
    }
    return InsuranceCoverageResponse.builder()
        .id(insuranceCoverage.getId())
        .ancillaryId(insuranceCoverage.getAncillary().getId())
        .ancillaryName(insuranceCoverage.getAncillary().getName())
        .coverageType(insuranceCoverage.getCoverageType())
        .name(insuranceCoverage.getName())
        .description(insuranceCoverage.getDescription())
        .coverageAmount(insuranceCoverage.getCoverageAmount())
        .isFlat(insuranceCoverage.getIsFlat())
        .claimCondition(insuranceCoverage.getClaimCondition())
        .emergencyContact(insuranceCoverage.getEmergencyContact())
        .displayOrder(insuranceCoverage.getDisplayOrder())
        .active(insuranceCoverage.getActive())
        .build();
  }

  public static InsuranceCoverage updateEntity(InsuranceCoverage entity, InsuranceCoverageRequest request,
      Ancillary ancillary) {
    if (entity == null || request == null) {
      return null;
    }
    if (ancillary != null) {
      entity.setAncillary(ancillary);
    }
    if (request.getCoverageType() != null) {
      entity.setCoverageType(request.getCoverageType());
    }
    if (request.getName() != null) {
      entity.setName(request.getName());
    }
    if (request.getDescription() != null) {
      entity.setDescription(request.getDescription());
    }
    if (request.getCoverageAmount() != null) {
      entity.setCoverageAmount(request.getCoverageAmount());
    }
    if (request.getIsFlat() != null) {
      entity.setIsFlat(request.getIsFlat());
    }
    if (request.getClaimCondition() != null) {
      entity.setClaimCondition(request.getClaimCondition());
    }
    if (request.getEmergencyContact() != null) {
      entity.setEmergencyContact(request.getEmergencyContact());
    }
    if (request.getDisplayOrder() != null) {
      entity.setDisplayOrder(request.getDisplayOrder());
    }
    if (request.getActive() != null) {
      entity.setActive(request.getActive());
    }
    return entity;
  }
}
