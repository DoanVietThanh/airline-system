package com.thanhdoan.mapper;

import java.util.stream.Collectors;

import com.thanhdoan.model.FlightInstanceCabin;
import com.thanhdoan.payload.response.FlightInstanceCabinResponse;

public class FlightInstanceCabinMapper {

  public static FlightInstanceCabinResponse toResponse(FlightInstanceCabin entity) {
    if (entity == null) {
      return null;
    }
    return FlightInstanceCabinResponse.builder()
        .id(entity.getId())
        .flightInstanceId(entity.getFlightInstanceId())
        .cabinClassType(entity.getCabinClass().getName())
        .cabinClass(CabinClassMapper.toResponse(entity.getCabinClass(), entity.getCabinClass().getSeatMap()))
        .seats(entity.getSeats() != null
            ? entity.getSeats().stream().map(SeatInstanceMapper::toResponse).collect(Collectors.toList())
            : null)
        .seatMap(entity.getCabinClass() != null && entity.getCabinClass().getSeatMap() != null
            ? SeatMapMapper.toSimpleResponse(entity.getCabinClass().getSeatMap())
            : null)
        .totalSeats(entity.getTotalSeats())
        .bookedSeats(entity.getBookedSeats())
        .availableSeats(entity.getAvailableSeats())
        .build();
  }

}
