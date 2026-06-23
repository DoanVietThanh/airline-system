package com.thanhdoan.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanhdoan.enums.FlightStatus;
import com.thanhdoan.mapper.FlightScheduleMapper;
import com.thanhdoan.model.Flight;
import com.thanhdoan.model.FlightSchedule;
import com.thanhdoan.payload.request.FlightInstanceRequest;
import com.thanhdoan.payload.request.FlightScheduleRequest;
import com.thanhdoan.payload.response.AirportResponse;
import com.thanhdoan.payload.response.FlightScheduleResponse;
import com.thanhdoan.repository.FlightRepository;
import com.thanhdoan.repository.FlightScheduleRepository;
import com.thanhdoan.service.FlightInstanceService;
import com.thanhdoan.service.FlightScheduleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightScheduleImpl implements FlightScheduleService {

  private final FlightRepository flightRepository;
  private final FlightScheduleRepository flightScheduleRepository;
  private final FlightInstanceService flightInstanceService;

  @Override
  public FlightScheduleResponse createFlightSchedule(Long airlineId, FlightScheduleRequest request) throws Exception {
    Flight flight = flightRepository.findById(
        request.getFlightId()).orElseThrow(() -> new Exception("Flight not found"));

    if (request.getEndDate().isBefore(request.getStartDate())) {
      throw new Exception("End date must be after start date");
    }

    FlightSchedule flightSchedule = FlightScheduleMapper.toEntity(request, flight);
    FlightSchedule savedFlightSchedule = flightScheduleRepository.save(flightSchedule);

    List<DayOfWeek> operatingDays = request.getOperatingDays();
    LocalDate startDate = request.getStartDate();
    LocalDate endDate = request.getEndDate();

    FlightInstanceRequest flightInstanceRequest = FlightInstanceRequest.builder()
        .scheduleId(savedFlightSchedule.getId())
        .flightId(flight.getId())
        .departureAirportId(flight.getDepartureAirportId())
        .arrivalAirportId(flight.getArrivalAirportId())
        .status(FlightStatus.SCHEDULED)
        .build();

    for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
      if (operatingDays.contains(date.getDayOfWeek())) {
        flightInstanceRequest.setDepartureDateTime(LocalDateTime.of(date, savedFlightSchedule.getDepartureTime()));
        flightInstanceRequest.setArrivalDateTime(LocalDateTime.of(date, savedFlightSchedule.getArrivalTime()));
        flightInstanceService.createFlightInstance(airlineId, flightInstanceRequest);
      }
    }

    return convertToFlightScheduleResponse(savedFlightSchedule);
  }

  @Override
  public FlightScheduleResponse getFlightScheduleById(Long id) throws Exception {
    FlightSchedule flightSchedule = flightScheduleRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight schedule not found"));
    return convertToFlightScheduleResponse(flightSchedule);
  }

  @Override
  public List<FlightScheduleResponse> getFlightSchedulesByAirline(Long airlineId) {
    List<FlightSchedule> flightSchedules = flightScheduleRepository.findByAirlineId(airlineId);
    return flightSchedules.stream()
        .map(this::convertToFlightScheduleResponse)
        .collect(Collectors.toList());
  }

  @Override
  public FlightScheduleResponse updateFlightSchedule(Long id, FlightScheduleRequest request) throws Exception {
    FlightSchedule flightSchedule = flightScheduleRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight schedule not found"));
    FlightScheduleMapper.updateEntity(request, flightSchedule);
    FlightSchedule savedFlightSchedule = flightScheduleRepository.save(flightSchedule);
    return convertToFlightScheduleResponse(savedFlightSchedule);
  }

  @Override
  public void deleteFlightSchedule(Long id) throws Exception {
    FlightSchedule flightSchedule = flightScheduleRepository.findById(id)
        .orElseThrow(() -> new Exception("Flight schedule not found"));
    flightScheduleRepository.delete(flightSchedule);
  }

  private FlightScheduleResponse convertToFlightScheduleResponse(FlightSchedule flightSchedule) {
    AirportResponse departureAirport = AirportResponse.builder()
        .id(flightSchedule.getDepartureAirportId())
        .build();
    AirportResponse arrivalAirport = AirportResponse.builder()
        .id(flightSchedule.getArrivalAirportId())
        .build();
    return FlightScheduleMapper.toResponse(flightSchedule, departureAirport, arrivalAirport);
  }
}
