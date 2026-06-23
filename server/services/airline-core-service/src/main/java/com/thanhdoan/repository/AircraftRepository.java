package com.thanhdoan.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhdoan.model.Aircraft;
import com.thanhdoan.enums.AircraftStatus;
import com.thanhdoan.model.Airline;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

  Optional<Aircraft> findByCode(String code);

  boolean existsByCode(String code);

  List<Aircraft> findByStatus(AircraftStatus status);

  List<Aircraft> findByAirline(Airline airline);

  List<Aircraft> findByAirlineAndStatus(Airline airline, AircraftStatus status);

  List<Aircraft> findByAirlineAndStatusAndIsAvailable(Airline airline, AircraftStatus status, Boolean isAvailable);

  List<Aircraft> findByModelContainingIgnoreCase(String model);

  List<Aircraft> findByNextMaintenanceDateBefore(LocalDate date);
}
