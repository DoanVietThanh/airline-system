package com.thanhdoan.mapper;

import com.thanhdoan.model.CabinClass;
import com.thanhdoan.model.SeatMap;
import com.thanhdoan.payload.request.CabinClassRequest;
import com.thanhdoan.payload.response.CabinClassResponse;

public class CabinClassMapper {

  public static CabinClass toEntity(CabinClassRequest request) {
    if (request == null) {
      return null;
    }
    return CabinClass.builder()
        .name(request.getName())
        .code(request.getCode())
        .description(request.getDescription())
        .aircraftId(request.getAircraftId())
        .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
        .isActive(request.getIsActive() != null ? request.getIsActive() : true)
        .isBookable(request.getIsBookable() != null ? request.getIsBookable() : true)
        .typicalSeatPitch(request.getTypicalSeatPitch())
        .typicalSeatWidth(request.getTypicalSeatWidth())
        .seatType(request.getSeatType())
        .build();
  }

  // toResponse
  public static CabinClassResponse toResponse(CabinClass entity, SeatMap seatMap) {
    if (entity == null) {
      return null;
    }
    return CabinClassResponse.builder()
        .id(entity.getId())
        .name(entity.getName().name())
        .code(entity.getCode())
        .description(entity.getDescription())
        .aircraftId(entity.getAircraftId())
        .seatMap(seatMap != null ? SeatMapMapper.toResponse(seatMap) : null)
        .displayOrder(entity.getDisplayOrder())
        .isActive(entity.getIsActive())
        .isBookable(entity.getIsBookable())
        .typicalSeatPitch(entity.getTypicalSeatPitch())
        .typicalSeatWidth(entity.getTypicalSeatWidth())
        .seatType(entity.getSeatType())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }

  // updateEntity
  public static void toUpdateEntity(CabinClassRequest request, CabinClass entity) {
    if (request == null || entity == null) {
      return;
    }
    entity.setName(request.getName());
    entity.setCode(request.getCode());
    entity.setDescription(request.getDescription());
    entity.setAircraftId(request.getAircraftId());
    entity.setDisplayOrder(request.getDisplayOrder());
    entity.setIsActive(request.getIsActive());
    entity.setIsBookable(request.getIsBookable());
    entity.setTypicalSeatPitch(request.getTypicalSeatPitch());
    entity.setTypicalSeatWidth(request.getTypicalSeatWidth());
    entity.setSeatType(request.getSeatType());
  }

}
