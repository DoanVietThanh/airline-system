package com.thanhdoan.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thanhdoan.model.FlightInstance;

public interface FlightInstanceRepository extends JpaRepository<FlightInstance, Long> {

	@Query("""
			SELECT fi FROM FlightInstance fi
			WHERE fi.airlineId = :airlineId
			AND (:departureAirportId IS NULL OR fi.departureAirportId = :departureAirportId)
			AND (:arrivalAirportId IS NULL OR fi.arrivalAirportId = :arrivalAirportId)
			AND (:flightId IS NULL OR fi.flightId = :flightId)
			AND (:dayStart IS NULL OR fi.dayStart >= :dayStart)
			AND (:dayEnd IS NULL OR fi.dayEnd <= :dayEnd)
			""")
	Page<FlightInstance> findByAirlineId(@Param("airlineId") Long airlineId,
			@Param("departureAirportId") Long departureAirportId,
			@Param("arrivalAirportId") Long arrivalAirportId, @Param("flightId") Long flightId,
			@Param("dayStart") LocalDateTime dayStart, @Param("dayEnd") LocalDateTime dayEnd, Pageable pageable);

	boolean existsByFlightIdAndScheduleId(Long flightId, Long scheduleId);

	boolean existsByFlightIdAndScheduleIdAndIdNot(Long flightId, Long scheduleId, Long id);

	boolean existsByFlightIdAndScheduleIdAndDepartureAirportIdAndArrivalAirportId(Long flightId, Long scheduleId,
			Long departureAirportId, Long arrivalAirportId);
}
