package com.thanhdoan.mapper;

import com.thanhdoan.model.Flight;
import com.thanhdoan.model.FlightSchedule;
import com.thanhdoan.payload.request.FlightScheduleRequest;
import com.thanhdoan.payload.response.AirportResponse;
import com.thanhdoan.payload.response.FlightScheduleResponse;

public class FlightScheduleMapper {
  public static FlightSchedule toEntity(FlightScheduleRequest request, Flight flight) {
    if (request == null || flight == null)
      return null;

    return FlightSchedule.builder()
        .flight(flight)
        .departureAirportId(flight.getDepartureAirportId())
        .arrivalAirportId(flight.getArrivalAirportId())
        .departureTime(request.getDepartureTime())
        .arrivalTime(request.getArrivalTime())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .operatingDays(request.getOperatingDays())
        .isActive(request.getIsActive() != null ? request.getIsActive() : true)
        .build();
  }

  public static FlightScheduleResponse toResponse(FlightSchedule entity, AirportResponse departureAirportResponse,
      AirportResponse arrivalAirportResponse) {

    if (entity == null)
      return null;
    return FlightScheduleResponse.builder()
        .id(entity.getId())
        .flightId(entity.getFlight() != null ? entity.getFlight().getId() : null)
        .flightNumber(entity.getFlight() != null ? entity.getFlight().getFlightNumber() : null)
        .departureAirport(departureAirportResponse)
        .arrivalAirport(arrivalAirportResponse)
        .departureTime(entity.getDepartureTime())
        .arrivalTime(entity.getArrivalTime())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
        .operatingDays(entity.getOperatingDays())
        .isActive(entity.getIsActive())
        .build();
  }

  public static void updateEntity(FlightScheduleRequest request, FlightSchedule entity) {
    if (request == null || entity == null)
      return;
    if (request.getDepartureTime() != null)
      entity.setDepartureTime(request.getDepartureTime());
    if (request.getArrivalTime() != null)
      entity.setArrivalTime(request.getArrivalTime());
    if (request.getStartDate() != null)
      entity.setStartDate(request.getStartDate());
    if (request.getEndDate() != null)
      entity.setEndDate(request.getEndDate());
    if (request.getOperatingDays() != null)
      entity.setOperatingDays(request.getOperatingDays());
    if (request.getIsActive() != null)
      entity.setIsActive(request.getIsActive());
  }

}
