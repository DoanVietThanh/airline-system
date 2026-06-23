package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.payload.request.AircraftRequest;
import com.thanhdoan.payload.response.AircraftResponse;

public interface AircraftService {

  AircraftResponse getAircraftById(Long id) throws Exception;

  List<AircraftResponse> listAllAircraftsByOwner(Long ownerId) throws Exception;

  AircraftResponse createAircraft(AircraftRequest request,
      Long ownerId) throws Exception;

  AircraftResponse updateAircraft(Long id, AircraftRequest request, Long ownerId) throws Exception;

  void deleteAircraft(Long id) throws Exception;
}
