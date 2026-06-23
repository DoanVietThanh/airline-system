package com.thanhdoan.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thanhdoan.mapper.FlightInstanceMapper;
import com.thanhdoan.model.Flight;
import com.thanhdoan.model.FlightInstance;
import com.thanhdoan.payload.request.FlightInstanceRequest;
import com.thanhdoan.payload.response.AircraftResponse;
import com.thanhdoan.payload.response.AirlineResponse;
import com.thanhdoan.payload.response.AirportResponse;
import com.thanhdoan.payload.response.FlightInstanceResponse;
import com.thanhdoan.repository.FlightInstanceRepository;
import com.thanhdoan.repository.FlightRepository;
import com.thanhdoan.service.FlightInstanceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightInstanceServiceImpl implements FlightInstanceService {

  private final FlightInstanceRepository flightInstanceRepository;
  private final FlightRepository flightRepository;

  @Override
  public FlightInstanceResponse createFlightInstance(Long userId, FlightInstanceRequest flightInstanceRequest)
      throws Exception {
    Flight fight = flightRepository.findById(flightInstanceRequest.getFlightId())
        .orElseThrow(() -> new Exception("Flight not found"));

    // dummy Aircraft
    AircraftResponse aircraft = AircraftResponse.builder()
        .id(1L)
        .totalSeats(90)
        .build();

    FlightInstance fi = FlightInstanceMapper.toEntity(flightInstanceRequest, fight);
    fi.setTotalSeats(aircraft.getTotalSeats());
    fi.setAvailableSeats(aircraft.getTotalSeats());

    FlightInstance savedFi = flightInstanceRepository.save(fi);

    return convertToFlightInstanceResponse(savedFi);
  }

  @Override
  public FlightInstanceResponse getFlightInstanceById(Long id) throws Exception {
    FlightInstance fi = flightInstanceRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight instance not found"));
    return convertToFlightInstanceResponse(fi);
  }

  @Override
  public FlightInstanceResponse updateFlightInstance(Long id, FlightInstanceRequest flightInstanceRequest)
      throws Exception {
    FlightInstance fi = flightInstanceRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight instance not found"));
    FlightInstanceMapper.updateEntity(flightInstanceRequest, fi);
    return convertToFlightInstanceResponse(flightInstanceRepository.save(fi));
  }

  @Override
  public void deleteFlightInstance(Long id) throws Exception {
    FlightInstance fi = flightInstanceRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight instance not found"));
    flightInstanceRepository.delete(fi);
  }

  @Override
  public Page<FlightInstanceResponse> getFlightInstancesByAirlineId(Long airlineId, Long departureAirportId,
      Long arrivalAirportId, Long flightId, LocalDate onDate, Pageable pageable) {
    LocalDateTime dayStart = onDate != null ? onDate.atStartOfDay() : null;
    LocalDateTime dayEnd = onDate != null ? onDate.atStartOfDay().plusDays(1) : null;
    Page<FlightInstance> fis = flightInstanceRepository.findByAirlineId(airlineId, departureAirportId, arrivalAirportId,
        flightId, dayStart, dayEnd, pageable);
    return fis.map(this::convertToFlightInstanceResponse);
  }

  private FlightInstanceResponse convertToFlightInstanceResponse(FlightInstance fi) {
    AirlineResponse airline = AirlineResponse.builder()
        .id(fi.getAirlineId())
        .build();
    AircraftResponse aircraft = AircraftResponse.builder()
        .id(fi.getFlight().getAircraftId())
        .build();
    AirportResponse departureAirport = AirportResponse.builder()
        .id(fi.getDepartureAirportId())
        .build();
    AirportResponse arrivalAirport = AirportResponse.builder()
        .id(fi.getArrivalAirportId())
        .build();
    return FlightInstanceMapper.toResponse(fi, aircraft, airline, departureAirport, arrivalAirport);
  }
}
