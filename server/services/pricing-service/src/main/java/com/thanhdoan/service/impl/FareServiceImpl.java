package com.thanhdoan.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanhdoan.mapper.FareMapper;
import com.thanhdoan.model.Fare;
import com.thanhdoan.payload.request.FareRequest;
import com.thanhdoan.payload.response.FareResponse;
import com.thanhdoan.repository.FareRepository;
import com.thanhdoan.service.FareService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FareServiceImpl implements FareService {
  private final FareRepository fareRepository;

  @Override
  public FareResponse createFare(FareRequest fareRequest) throws Exception {
    if (fareRepository.existsByFlightIdAndCabinClassIdAndName(
        fareRequest.getFlightId(),
        fareRequest.getCabinClassId(),
        fareRequest.getName())) {
      throw new Exception("Fare already exists");
    }
    Fare fare = FareMapper.toEntity(fareRequest);
    fare = fareRepository.save(fare);
    return FareMapper.toResponse(fare);
  }

  @Override
  public FareResponse getFareById(Long id) throws Exception {
    Fare fare = fareRepository.findById(id).orElseThrow(() -> new Exception("Fare not found"));
    return FareMapper.toResponse(fare);
  }

  @Override
  public List<FareResponse> getFaresByFlightIdAndCabinCLassId(Long flightId, Long cabinClassId) {
    List<Fare> fares = fareRepository.findByFlightIdAndCabinClassId(flightId, cabinClassId);
    return fares.stream().map(FareMapper::toResponse).collect(Collectors.toList());
  }

  @Override
  public FareResponse updateFare(Long id, FareRequest fareRequest) throws Exception {
    Fare fare = fareRepository.findById(id).orElseThrow(() -> new Exception("Fare not found"));
    if (fareRepository.existsByFlightIdAndCabinClassIdAndNameAndIdNot(fareRequest.getFlightId(),
        fareRequest.getCabinClassId(), fareRequest.getName(), id)) {
      throw new Exception("Fare already exists");
    }
    FareMapper.updateEntity(fareRequest, fare);
    fare = fareRepository.save(fare);
    return FareMapper.toResponse(fare);
  }

  @Override
  public void deleteFare(Long id) throws Exception {
    Fare fare = fareRepository.findById(id).orElseThrow(() -> new Exception("Fare not found"));
    fareRepository.delete(fare);
  }

  @Override
  public List<Fare> getFares() {
    List<Fare> fares = fareRepository.findAll();
    return fares;
  }

  @Override
  public Map<Long, FareResponse> getLowestFarePerFlight(List<Long> flightIds, Long cabinClassId) throws Exception {
    if (flightIds == null || flightIds.isEmpty()) {
      throw new Exception("Flight IDs cannot be null or empty");
    }
    if (cabinClassId == null) {
      throw new Exception("Cabin class ID cannot be null");
    }
    List<Fare> fares = fareRepository.findByFlightIdInAndCabinClassId(flightIds, cabinClassId);
    Map<Long, Fare> lowestFares = fares.stream()
        .collect(Collectors.toMap(Fare::getFlightId, fare -> fare,
            (existing, candidate) -> candidate.getTotalPrice() < existing.getTotalPrice()
                ? candidate
                : existing));
    return lowestFares.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, entry -> FareMapper.toResponse(entry.getValue())));
  }

  @Override
  public Map<Long, FareResponse> getFaresByIds(List<Long> ids) {
    List<Fare> fares = fareRepository.findAllById(ids);
    return fares.stream()
        .collect(Collectors.toMap(Fare::getId, FareMapper::toResponse));
  }
}
