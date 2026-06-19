package com.thanhdoan.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thanhdoan.enums.AirlineStatus;
import com.thanhdoan.payload.request.AirlineRequest;
import com.thanhdoan.payload.response.AirlineDropdownItem;
import com.thanhdoan.payload.response.AirlineResponse;

public interface AirlineService {
  // ----- CRUD -----
  AirlineResponse createAirline(AirlineRequest request, Long ownerId);

  AirlineResponse getAirlineByOwner(Long ownerId) throws Exception;

  AirlineResponse getAirlineById(Long id) throws Exception;

  Page<AirlineResponse> getAllAirlines(Pageable pageable);

  AirlineResponse updateAirline(AirlineRequest request, Long ownerId) throws Exception;

  void deleteAirline(Long id, Long ownerId) throws Exception;

  AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) throws Exception;

  // ----- Dropdown -----
  List<AirlineDropdownItem> getAirlinesForDropdown();
}
