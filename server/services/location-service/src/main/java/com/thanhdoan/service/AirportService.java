package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.payload.request.AirportRequest;
import com.thanhdoan.payload.response.AirportResponse;

public interface AirportService {
  AirportResponse createAirport(AirportRequest request) throws Exception;

  // List<AirportResponse> createBulkAirports(List<AirportRequest> requests)
  // throws AirportException, CityException;

  AirportResponse getAirportById(Long id) throws Exception;

  List<AirportResponse> getAllAirports();

  AirportResponse updateAirport(Long id, AirportRequest request) throws Exception;

  void deleteAirport(Long id) throws Exception;

  List<AirportResponse> getAirportsByCityId(Long cityId);
}
