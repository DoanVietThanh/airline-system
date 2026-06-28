package com.thanhdoan.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.thanhdoan.model.CabinClass;
import com.thanhdoan.model.Seat;
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
    if (seatMap == null) {
      return null;
    }

    List<Seat> seats = seatMap.getSeats();

    int totalSeats = seats != null ? seats.size() : 0;
    int availableSeats = seats != null
        ? (int) seats.stream().filter(seat -> {
          return Boolean.TRUE.equals(seat.getIsAvailable())
              && Boolean.TRUE.equals(seat.getIsActive())
              && !Boolean.TRUE.equals(seat.getIsBlocked());
        }).count()
        : 0;

    int windowSeats = seats != null
        ? (int) seats.stream().filter(seat -> seat.getSeatType().name().contains("WINDOW")).count()
        : 0;

    int aisleSeats = seats != null
        ? (int) seats.stream().filter(seat -> seat.getSeatType().name().contains("AISLE")).count()
        : 0;

    int middleSeats = seats != null
        ? (int) seats.stream().filter(seat -> seat.getSeatType().name().contains("MIDDLE")).count()
        : 0;

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
        .totalSeats(totalSeats)
        .availableSeats(availableSeats)
        .occupiedSeats(totalSeats - availableSeats)
        .seats(seats != null ? seats.stream().map(SeatMapper::toResponse).collect(Collectors.toList()) : null)
        .windowSeats(windowSeats)
        .aisleSeats(aisleSeats)
        .middleSeats(middleSeats)
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

  public static SeatMapResponse toSimpleResponse(SeatMap seatMap) {
    return SeatMapResponse.builder()
        .totalRows(seatMap.getTotalRows())
        .rightSeatsPerRow(seatMap.getRightSeatsPerRow())
        .leftSeatsPerRow(seatMap.getLeftSeatsPerRow())
        .build();
  }
}
