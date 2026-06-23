package com.thanhdoan.mapper;

import com.thanhdoan.model.Flight;
import com.thanhdoan.payload.request.FlightRequest;
import com.thanhdoan.payload.response.AircraftResponse;
import com.thanhdoan.payload.response.AirlineResponse;
import com.thanhdoan.payload.response.AirportResponse;
import com.thanhdoan.payload.response.FlightResponse;

public class FlightMapper {

  public static Flight toEntity(FlightRequest flightRequest) {
    if (flightRequest == null)
      return null;

    return Flight.builder()
        .flightNumber(flightRequest.getFlightNumber())
        .airlineId(flightRequest.getAirlineId())
        .aircraftId(flightRequest.getAircraftId())
        .departureAirportId(flightRequest.getDepartureAirportId())
        .arrivalAirportId(flightRequest.getArrivalAirportId())
        .build();
  }

  public static FlightResponse toResponse(
      Flight flight, AircraftResponse aircraftResponse, AirlineResponse airlineResponse,
      AirportResponse departureAirportResponse, AirportResponse arrivalAirportResponse) {
    if (flight == null)
      return null;

    return FlightResponse.builder()
        .id(flight.getId())
        .flightNumber(flight.getFlightNumber())
        .airline(airlineResponse)
        .aircraft(aircraftResponse)
        .departureAirport(departureAirportResponse)
        .arrivalAirport(arrivalAirportResponse)
        .status(flight.getStatus())
        .createdAt(flight.getCreatedAt())
        .updatedAt(flight.getUpdatedAt())
        .build();
  }

  public static void updateEntity(FlightRequest flightRequest, Flight existingFlight) {
    if (existingFlight == null || flightRequest == null)
      return;
    if (flightRequest.getFlightNumber() != null)
      existingFlight.setFlightNumber(flightRequest.getFlightNumber());
    if (flightRequest.getAircraftId() != null)
      existingFlight.setAircraftId(flightRequest.getAircraftId());
    if (flightRequest.getDepartureAirportId() != null)
      existingFlight.setDepartureAirportId(flightRequest.getDepartureAirportId());
    if (flightRequest.getArrivalAirportId() != null)
      existingFlight.setArrivalAirportId(flightRequest.getArrivalAirportId());
    if (flightRequest.getStatus() != null)
      existingFlight.setStatus(flightRequest.getStatus());

  }
}
