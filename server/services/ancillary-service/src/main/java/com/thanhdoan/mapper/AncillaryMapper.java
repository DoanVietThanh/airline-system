package com.thanhdoan.mapper;

import java.util.List;

import com.thanhdoan.model.Ancillary;
import com.thanhdoan.payload.request.AncillaryRequest;
import com.thanhdoan.payload.response.AncillaryResponse;
import com.thanhdoan.payload.response.InsuranceCoverageResponse;

public class AncillaryMapper {

  // toEntity
  public static Ancillary toEntity(AncillaryRequest ancillaryRequest, Long airlineId) {
    return Ancillary.builder()
        .type(ancillaryRequest.getType())
        .subType(ancillaryRequest.getSubType())
        .rfisc(ancillaryRequest.getRfisc())
        .name(ancillaryRequest.getName())
        .description(ancillaryRequest.getDescription())
        .metadata(ancillaryRequest.getMetadata())
        .displayOrder(ancillaryRequest.getDisplayOrder())
        .airlineId(airlineId)
        .build();
  }

  // toResponse
  public static AncillaryResponse toResponse(Ancillary ancillary, List<InsuranceCoverageResponse> insuranceCoverages) {
    if (ancillary == null) {
      return null;
    }
    return AncillaryResponse.builder()
        .id(ancillary.getId())
        .type(ancillary.getType())
        .subType(ancillary.getSubType())
        .rfisc(ancillary.getRfisc())
        .name(ancillary.getName())
        .description(ancillary.getDescription())
        .metadata(ancillary.getMetadata())
        .converages(insuranceCoverages)
        .displayOrder(ancillary.getDisplayOrder())
        .airlineId(ancillary.getAirlineId())
        .build();
  }
}
