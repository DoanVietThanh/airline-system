package com.thanhdoan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.thanhdoan.model.FlightSchedule;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {
  List<FlightSchedule> findByAirlineId(Long airlineId);
}
