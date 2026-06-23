package com.thanhdoan.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thanhdoan.enums.FlightStatus;
import com.thanhdoan.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

  @Query("""
      SELECT f FROM Flight f
      WHERE f.airlineId = :airlineId
      AND (:departureAirportId IS NULL OR f.departureAirportId = :departureAirportId)
      AND (:arrivalAirportId IS NULL OR f.arrivalAirportId = :arrivalAirportId)
      """)
  Page<Flight> findByAirlineId(
      @Param("airlineId") Long airlineId,
      @Param("departureAirportId") Long departureAirportId,
      @Param("arrivalAirportId") Long arrivalAirportId,
      Pageable pageable);

  Optional<Flight> findByIdAndAirlineId(Long airlineId, Long id);

  boolean existsByFlightNumber(String flightNumber);

  boolean existsByFlightNumberAndIdNot(String flightNumber, Long id);

  Page<Flight> findByAircraftId(Long aircraftId, Pageable pageable);

  Page<Flight> findByDepartureAirportId(Long departureAirportId, Pageable pageable);

  Page<Flight> findByArrivalAirportId(Long arrivalAirportId, Pageable pageable);

  Page<Flight> findByStatus(FlightStatus status, Pageable pageable);
}
