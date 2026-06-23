package com.thanhdoan.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thanhdoan.enums.FlightStatus;
import com.thanhdoan.payload.request.FlightRequest;
import com.thanhdoan.payload.response.FlightResponse;

public interface FlightService {
	FlightResponse createFlight(Long airlineId, FlightRequest flightRequest) throws Exception;

	FlightResponse getFlightById(Long id) throws Exception;

	FlightResponse updateFlight(Long id, FlightRequest flightRequest) throws Exception;

	FlightResponse changeStatus(Long id, FlightStatus status) throws Exception;

	void deleteFlight(Long airlineId, Long id) throws Exception;

	List<FlightResponse> getAllFlights();

	Page<FlightResponse> getFlightsByAirline(Long airlineId, Long departureAirportId, Long arrivalAirportId,
			Pageable pageable);

}