package com.thanhdoan.mapper;

import com.thanhdoan.model.Seat;
import com.thanhdoan.payload.request.SeatRequest;
import com.thanhdoan.payload.response.SeatResponse;

public class SeatMapper {

  // to response
  public static SeatResponse toResponse(Seat seat) {
    return SeatResponse.builder()
        .id(seat.getId())
        .seatNumber(seat.getSeatNumber())
        .seatRow(seat.getSeatRow())
        .columnLetter(seat.getColumnLetter())
        .seatType(seat.getSeatType())
        .isAvailable(seat.getIsAvailable())
        .isBlocked(seat.getIsBlocked())
        .isActive(seat.getIsActive())
        .basePrice(seat.getBasePrice())
        .premiumSuperCharge(seat.getPremiumSuperCharge())
        .totalPrice(seat.getTotalPrice())

        .hasExtraLegroom(seat.getHasExtraLegroom())
        .hasPowerOutlet(seat.getHasPowerOutlet())
        .hasTvScreen(seat.getHasTvScreen())

        .hasExtraWidth(seat.getHasExtraWidth())
        .seatPitch(seat.getSeatPitch())

        .seatMapId(seat.getSeatMap() != null ? seat.getSeatMap().getId() : null)
        .seatMapName(seat.getSeatMap() != null ? seat.getSeatMap().getName() : null)
        .cabinClassId(seat.getCabinClass() != null ? seat.getCabinClass().getId() : null)
        .cabinClassName(seat.getCabinClass() != null ? seat.getCabinClass().getName().toString() : null)
        .createdAt(seat.getCreatedAt())
        .updatedAt(seat.getUpdatedAt())
        .createdBy(seat.getCreatedBy())
        .updatedBy(seat.getUpdatedBy())

        // .isBookable(seat.isBookable() )
        .fullPosition(seat.getFullPosition())

        .build();
  }

  // update
  public static Seat updateEntity(Seat seat, SeatRequest seatRequest) {
    return Seat.builder()
        .seatNumber(seatRequest.getSeatNumber())
        .seatRow(seatRequest.getSeatRow())
        .seatType(seatRequest.getSeatType())
        .columnLetter(seatRequest.getColumnLetter())
        .seatMap(seat.getSeatMap())
        .cabinClass(seat.getCabinClass())
        .basePrice(seatRequest.getBasePrice())
        .premiumSuperCharge(seatRequest.getPremiumSuperCharge())
        .hasExtraLegroom(seatRequest.getHasExtraLegroom())
        .hasPowerOutlet(seatRequest.getHasPowerOutlet())
        .hasTvScreen(seatRequest.getHasTvScreen())
        .hasExtraWidth(seatRequest.getHasExtraWidth())
        .seatPitch(seatRequest.getSeatPitch())
        .seatWidth(seatRequest.getSeatWidth())
        .isAvailable(seatRequest.getIsAvailable())
        .isBlocked(seatRequest.getIsBlocked())
        .isActive(seatRequest.getIsActive())
        .hasExtraLegroom(seatRequest.getHasExtraLegroom())
        .hasPowerOutlet(seatRequest.getHasPowerOutlet())
        .hasTvScreen(seatRequest.getHasTvScreen())
        .hasExtraWidth(seatRequest.getHasExtraWidth())
        .createdAt(seat.getCreatedAt())
        .updatedAt(seat.getUpdatedAt())
        .createdBy(seat.getCreatedBy())
        .updatedBy(seat.getUpdatedBy())
        .version(seat.getVersion())
        .build();
  }
}