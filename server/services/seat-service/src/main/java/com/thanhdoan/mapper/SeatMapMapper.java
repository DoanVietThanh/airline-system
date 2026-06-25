package com.thanhdoan.mapper;

import com.thanhdoan.model.CabinClass;
import com.thanhdoan.model.SeatMap;
import com.thanhdoan.payload.request.SeatMapRequest;
import com.thanhdoan.payload.response.SeatMapResponse;

public class SeatMapMapper {

  public static SeatMap toEntity(SeatMapRequest request, CabinClass cabinClass) {
    return SeatMap.builder()
        .name(request.getName())
        .totalRows(request.getTotalRows())
        .rightSeatsPerRow(request.getRightSeatsPerRow())
        .leftSeatsPerRow(request.getLeftSeatsPerRow())
        .cabinClass(cabinClass)
        .build();
  }

  public static SeatMapResponse toResponse(SeatMap seatMap) {
    return SeatMapResponse.builder()
        .id(seatMap.getId())
        .name(seatMap.getName())
        .totalRows(seatMap.getTotalRows())
        .rightSeatsPerRow(seatMap.getRightSeatsPerRow())
        .leftSeatsPerRow(seatMap.getLeftSeatsPerRow())
        .airlineId(seatMap.getAirlineId())
        .cabinClassId(seatMap.getCabinClass() != null ? seatMap.getCabinClass().getId() : null)
        .cabinClassName(seatMap.getCabinClass() != null ? seatMap.getCabinClass().getName().toString() : null)
        .cabinClassCode(seatMap.getCabinClass() != null ? seatMap.getCabinClass().getCode() : null)
        // .totalSeats(seatMap.getTotalRows() * seatMap.getRightSeatsPerRow() +
        // seatMap.getLeftSeatsPerRow())
        // .availableSeats(seatMap.getTotalRows() * seatMap.getRightSeatsPerRow() +
        // seatMap.getLeftSeatsPerRow())
        // .occupiedSeats(0)
        // .windowSeats(seatMap.getTotalRows() * seatMap.getRightSeatsPerRow())
        // .aisleSeats(seatMap.getTotalRows() * seatMap.getLeftSeatsPerRow())
        // .middleSeats(0)
        // .premiumSeats(0)
        // .emergencyExitSeats(0)
        // .leftSeatsPerRow(seatMap.getLeftSeatsPerRow())
        // .rightSeatsPerRow(seatMap.getRightSeatsPerRow())
        // .seats(seatMap.getSeats().stream()
        // .map(SeatMapper::toResponse)
        // .collect(Collectors.toList()))
        .build();
  }

  // updateEntity
  public static void updateEntity(SeatMap seatMap, SeatMapRequest request) throws Exception {
    if (request.getName() != null) {
      seatMap.setName(request.getName());
    }
    if (request.getTotalRows() != null) {
      seatMap.setTotalRows(request.getTotalRows());
    }
    if (request.getRightSeatsPerRow() != null) {
      seatMap.setRightSeatsPerRow(request.getRightSeatsPerRow());
    }
    if (request.getLeftSeatsPerRow() != null) {
      seatMap.setLeftSeatsPerRow(request.getLeftSeatsPerRow());
    }
  }
}
