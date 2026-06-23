package com.thanhdoan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thanhdoan.enums.FlightStatus;
import com.thanhdoan.mapper.FlightMapper;
import com.thanhdoan.model.Flight;
import com.thanhdoan.payload.request.FlightRequest;
import com.thanhdoan.payload.response.AircraftResponse;
import com.thanhdoan.payload.response.AirlineResponse;
import com.thanhdoan.payload.response.AirportResponse;
import com.thanhdoan.payload.response.FlightResponse;
import com.thanhdoan.service.FlightService;

import lombok.RequiredArgsConstructor;

import com.thanhdoan.repository.FlightRepository;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

  private final FlightRepository flightRepository;

  @Override
  public FlightResponse createFlight(Long airlineId, FlightRequest flightRequest) throws Exception {
    if (flightRepository.existsByFlightNumber(flightRequest.getFlightNumber())) {
      throw new Exception("Flight number already exists");
    }
    Flight flight = FlightMapper.toEntity(flightRequest);
    flight.setAirlineId(airlineId);
    flight = flightRepository.save(flight);
    return convertToFlightResponse(flight);
  }

  @Override
  public FlightResponse getFlightById(Long id) throws Exception {
    Flight flight = flightRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight not found"));
    return convertToFlightResponse(flight);
  }

  @Override
  public FlightResponse updateFlight(Long id, FlightRequest flightRequest) throws Exception {
    Flight flight = flightRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight not found"));
    if (flightRepository.existsByFlightNumberAndIdNot(flightRequest.getFlightNumber(), id)) {
      throw new Exception("Flight number already exists");
    }
    FlightMapper.updateEntity(flightRequest, flight);
    flight = flightRepository.save(flight);
    return convertToFlightResponse(flight);
  }

  @Override
  public FlightResponse changeStatus(Long id, FlightStatus status) throws Exception {
    Flight existingFlight = flightRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight not found"));
    existingFlight.setStatus(status);
    Flight savedFlight = flightRepository.save(existingFlight);
    return convertToFlightResponse(savedFlight);
  }

  @Override
  public void deleteFlight(Long airlineId, Long id) throws Exception {
    Flight existingFlight = flightRepository.findByIdAndAirlineId(airlineId, id)
        .orElseThrow(() -> new Exception("Flight not found for airline: " + airlineId));
    flightRepository.delete(existingFlight);
  }

  @Override
  public List<FlightResponse> getAllFlights() {
    List<Flight> flights = flightRepository.findAll();
    return flights.stream().map(this::convertToFlightResponse).collect(Collectors.toList());
  }

  @Override
  public Page<FlightResponse> getFlightsByAirline(Long airlineId, Long departureAirportId, Long arrivalAirportId,
      Pageable pageable) {
    Page<Flight> flights = flightRepository.findByAirlineId(airlineId, departureAirportId, arrivalAirportId, pageable);
    return flights.map(this::convertToFlightResponse);
  }

  public FlightResponse convertToFlightResponse(Flight flight) {
    AircraftResponse aircraftResponse = AircraftResponse.builder().id(flight.getAircraftId()).build();
    AirlineResponse airlineResponse = AirlineResponse.builder().id(flight.getAirlineId()).build();
    AirportResponse departureAirportResponse = AirportResponse.builder().id(flight.getDepartureAirportId()).build();
    AirportResponse arrivalAirportResponse = AirportResponse.builder().id(flight.getArrivalAirportId()).build();
    return FlightMapper.toResponse(flight, aircraftResponse, airlineResponse, departureAirportResponse,
        arrivalAirportResponse);
  }

}
