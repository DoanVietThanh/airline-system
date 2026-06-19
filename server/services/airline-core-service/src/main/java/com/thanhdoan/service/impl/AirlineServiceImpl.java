package com.thanhdoan.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thanhdoan.enums.AirlineStatus;
import com.thanhdoan.mapper.AirlineMapper;
import com.thanhdoan.model.Airline;
import com.thanhdoan.repository.AirlineRepository;
import com.thanhdoan.payload.request.AirlineRequest;
import com.thanhdoan.payload.response.AirlineDropdownItem;
import com.thanhdoan.payload.response.AirlineResponse;
import com.thanhdoan.service.AirlineService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

  private final AirlineRepository airlineRepository;

  @Override
  public AirlineResponse createAirline(AirlineRequest request, Long ownerId) {
    Airline airline = AirlineMapper.toEntity(request, ownerId);
    Airline saved = airlineRepository.save(airline);
    return AirlineMapper.toResponse(saved);
  }

  @Override
  public AirlineResponse getAirlineByOwner(Long ownerId) throws Exception {
    Airline airline = airlineRepository.findByOwnerId(ownerId)
        .orElseThrow(() -> new Exception("Airline not found for owner: " + ownerId));
    return AirlineMapper.toResponse(airline);
  }

  @Override
  public AirlineResponse getAirlineById(Long id) throws Exception {
    Airline airline = airlineRepository.findById(id)
        .orElseThrow(() -> new Exception("Airline not found"));
    return AirlineMapper.toResponse(airline);
  }

  @Override
  public Page<AirlineResponse> getAllAirlines(Pageable pageable) {
    return airlineRepository
        .findAll(pageable).map(AirlineMapper::toResponse);
  }

  @Override
  public AirlineResponse updateAirline(AirlineRequest request, Long ownerId) throws Exception {
    Airline airline = airlineRepository.findByOwnerId(ownerId)
        .orElseThrow(() -> new Exception("Airline not found for owner: " + ownerId));

    AirlineMapper.updateEntity(airline, request);
    return AirlineMapper.toResponse(airlineRepository.save(airline));
  }

  @Override
  public void deleteAirline(Long id, Long ownerId) throws Exception {
    Airline airline = airlineRepository.findByOwnerId(ownerId)
        .orElseThrow(() -> new Exception("Airline not found for owner: " + ownerId));
    airlineRepository.delete(airline);
  }

  @Override
  public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) throws Exception {
    Airline airline = airlineRepository.findById(airlineId)
        .orElseThrow(() -> new Exception("Airline not found with ID: " + airlineId));
    airline.setStatus(status);
    return AirlineMapper.toResponse(airlineRepository.save(airline));
  }

  @Override
  public List<AirlineDropdownItem> getAirlinesForDropdown() {
    return airlineRepository.findByStatus(AirlineStatus.ACTIVE).stream()
        .map(a -> AirlineDropdownItem.builder()
            .id(a.getId())
            .name(a.getName())
            .iataCode(a.getIataCode())
            .icaoCode(a.getIcaoCode())
            .logoUrl(a.getLogoUrl())
            .country(a.getCountry())
            .build())
        .toList();
  }

}
