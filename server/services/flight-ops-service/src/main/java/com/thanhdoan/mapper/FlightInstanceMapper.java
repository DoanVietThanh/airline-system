package com.thanhdoan.mapper;

import com.thanhdoan.enums.FlightStatus;
import com.thanhdoan.model.Flight;
import com.thanhdoan.model.FlightInstance;
import com.thanhdoan.payload.request.FlightInstanceRequest;
import com.thanhdoan.payload.response.AircraftResponse;
import com.thanhdoan.payload.response.AirlineResponse;
import com.thanhdoan.payload.response.AirportResponse;
import com.thanhdoan.payload.response.FlightInstanceResponse;

public class FlightInstanceMapper {
  public static FlightInstance toEntity(FlightInstanceRequest flightInstanceRequest, Flight flight) {
    if (flightInstanceRequest == null || flight == null)
      return null;

    return FlightInstance.builder()
        .flight(flight)
        .airlineId(flight.getAirlineId())
        .scheduleId(flightInstanceRequest.getScheduleId())
        .departureAirportId(
            flightInstanceRequest.getDepartureAirportId() != null ? flightInstanceRequest.getDepartureAirportId()
                : null)
        .arrivalAirportId(
            flightInstanceRequest.getArrivalAirportId() != null ? flightInstanceRequest.getArrivalAirportId()
                : null)
        .departureDateTime(flightInstanceRequest.getDepartureDateTime())
        .arrivalDateTime(flightInstanceRequest.getArrivalDateTime())
        .status(FlightStatus.SCHEDULED)
        .minAdvanceBookingDays(flightInstanceRequest.getMinAdvanceBookingDays())
        .maxAdvanceBookingDays(flightInstanceRequest.getMaxAdvanceBookingDays())
        .isActive(flightInstanceRequest.getIsActive() != null ? flightInstanceRequest.getIsActive() : true)
        .build();
  }

  // toResponse
  public static FlightInstanceResponse toResponse(FlightInstance fi, AircraftResponse aircraftResponse,
      AirlineResponse airlineResponse, AirportResponse departureAirportResponse,
      AirportResponse arrivalAirportResponse) {

    if (fi == null)
      return null;

    return FlightInstanceResponse.builder()
        .id(fi.getId())
        .flightId(fi.getFlight() != null ? fi.getFlight().getId() : null)
        .flightNumber(fi.getFlight() != null ? fi.getFlight().getFlightNumber() : null)
        .aircraftId(fi.getFlight().getAircraftId())
        .aircraftModal(aircraftResponse.getModel())
        .aircraftCode(aircraftResponse.getCode())
        .airlineId(fi.getAirlineId())
        .airlineName(airlineResponse.getName())
        .airlineLogo(airlineResponse.getLogoUrl())
        .departureAirport(departureAirportResponse)
        .arrivalAirport(arrivalAirportResponse)
        .departureDateTime(fi.getDepartureDateTime())
        .arrivalDateTime(fi.getArrivalDateTime())
        .formattedDuration(fi.getFormattedDuration())
        .totalSeats(fi.getTotalSeats())
        .availableSeats(fi.getAvailableSeats())
        .status(fi.getStatus())
        .minAdvanceBookingDays(fi.getMinAdvanceBookingDays())
        .maxAdvanceBookingDays(fi.getMaxAdvanceBookingDays())
        .isActive(fi.getIsActive())
        .build();
  }

  // update entity
  public static void updateEntity(FlightInstanceRequest flightInstanceRequest, FlightInstance fi) {
    if (fi == null || flightInstanceRequest == null)
      return;

    if (flightInstanceRequest.getDepartureAirportId() != null) {
      fi.setDepartureAirportId(flightInstanceRequest.getDepartureAirportId());
    }
    if (flightInstanceRequest.getArrivalAirportId() != null) {
      fi.setArrivalAirportId(flightInstanceRequest.getArrivalAirportId());
    }
    if (flightInstanceRequest.getDepartureDateTime() != null) {
      fi.setDepartureDateTime(flightInstanceRequest.getDepartureDateTime());
    }
    if (flightInstanceRequest.getArrivalDateTime() != null) {
      fi.setArrivalDateTime(flightInstanceRequest.getArrivalDateTime());
    }
    if (flightInstanceRequest.getStatus() != null) {
      fi.setStatus(flightInstanceRequest.getStatus());
    }
    if (flightInstanceRequest.getMinAdvanceBookingDays() != null) {
      fi.setMinAdvanceBookingDays(flightInstanceRequest.getMinAdvanceBookingDays());
    }
    if (flightInstanceRequest.getMaxAdvanceBookingDays() != null) {
      fi.setMaxAdvanceBookingDays(flightInstanceRequest.getMaxAdvanceBookingDays());
    }
    if (flightInstanceRequest.getIsActive() != null) {
      fi.setIsActive(flightInstanceRequest.getIsActive());
    }
    if (flightInstanceRequest.getAvailableSeats() != null) {
      fi.setAvailableSeats(flightInstanceRequest.getAvailableSeats());
    }
  }
}
