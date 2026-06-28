package com.thanhdoan.mapper;

import com.thanhdoan.enums.SeatAvailableStatus;
import com.thanhdoan.model.SeatInstance;
import com.thanhdoan.payload.request.SeatInstanceRequest;
import com.thanhdoan.payload.response.SeatInstanceResponse;

public class SeatInstanceMapper {
  public static SeatInstanceResponse toResponse(SeatInstance seatInstance) {
    return SeatInstanceResponse.builder()
        .id(seatInstance.getId())
        .flightId(seatInstance.getFlightId())
        .seatId(seatInstance.getSeat() != null ? seatInstance.getSeat().getId() : null)
        .seatNumber(seatInstance.getSeat() != null ? seatInstance.getSeat().getSeatNumber() : null)
        .seatType(seatInstance.getSeat() != null ? seatInstance.getSeat().getSeatType().name() : null)
        .seatPosition(seatInstance.getSeat() != null ? seatInstance.getSeat().getFullPosition() : null)
        .seat(SeatMapper.toResponse(seatInstance.getSeat()))
        .status(seatInstance.getStatus())
        .flightInstanceId(seatInstance.getFlightInstanceCabin() != null
            ? seatInstance.getFlightInstanceCabin().getId()
            : null)
        .flightCabinId(seatInstance.getFlightInstanceCabin().getId())
        .fare(seatInstance.getFare())
        .price(seatInstance.getPremiumSupercharge())
        .version(seatInstance.getVersion())
        .createdAt(seatInstance.getCreatedAt())
        .updatedAt(seatInstance.getUpdatedAt())
        .isAvailable(seatInstance.isAvailable())
        .isBooked(seatInstance.isBooked())
        .isOccupied(seatInstance.getStatus() == SeatAvailableStatus.OCCUPIED)
        // .seatCharacteristics(seatInstance.getSeat() != null ?
        // seatInstance.getSeat().getSeatCharacteristics() : null)
        .build();
  }

  public static SeatInstance toEntity(SeatInstanceRequest request) {
    return SeatInstance.builder()
        .build();
  }
}
