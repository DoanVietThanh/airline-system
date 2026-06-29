package com.thanhdoan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanhdoan.enums.AncillaryType;
import com.thanhdoan.mapper.FlightCabinAncillaryMapper;
import com.thanhdoan.mapper.InsuranceCoverageMapper;
import com.thanhdoan.model.Ancillary;
import com.thanhdoan.model.FlightCabinAncillary;
import com.thanhdoan.model.InsuranceCoverage;
import com.thanhdoan.repository.AncillaryRepository;
import com.thanhdoan.repository.FlightCabinAncillaryRepository;
import com.thanhdoan.repository.InsuranceCoverageRepository;
import com.thanhdoan.payload.request.FlightCabinAncillaryRequest;
import com.thanhdoan.payload.response.FlightCabinAncillaryResponse;
import com.thanhdoan.payload.response.InsuranceCoverageResponse;
import com.thanhdoan.service.FlightCabinAncillaryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightCabinAncillaryServiceImpl implements FlightCabinAncillaryService {

  private final AncillaryRepository ancillaryRepository;
  private final InsuranceCoverageRepository insuranceCoverageRepository;
  private final FlightCabinAncillaryRepository flightCabinAncillaryRepository;

  @Override
  public FlightCabinAncillaryResponse createFlightCabinAncillary(
      FlightCabinAncillaryRequest request) throws Exception {
    Ancillary ancillary = ancillaryRepository.findById(request.getAncillaryId())
        .orElseThrow(() -> new Exception("Ancillary not found"));

    FlightCabinAncillary flightCabinAncillary = FlightCabinAncillary.builder()
        .flightId(request.getFlightId())
        .cabinClassId(request.getCabinClassId())
        .ancillary(ancillary)
        .available(request.getAvailable())
        .maxQuantity(request.getMaxQuantity())
        .price(request.getPrice())
        .includedInFare(request.getIncludedInFare())
        .build();

    FlightCabinAncillary savedFlightCabinAncillary = flightCabinAncillaryRepository.save(flightCabinAncillary);

    return convertToResponse(savedFlightCabinAncillary);
  }

  @Override
  public FlightCabinAncillaryResponse getFlightCabinAncillaryById(Long id) throws Exception {
    FlightCabinAncillary flightCabinAncillary = flightCabinAncillaryRepository.findById(id)
        .orElseThrow(() -> new Exception("FlightCabinAncillary not found"));
    return convertToResponse(flightCabinAncillary);
  }

  @Override
  public List<FlightCabinAncillaryResponse> getByFlightIdAndCabinClassId(Long flightId, Long cabinClassId) {
    List<FlightCabinAncillary> flightCabinAncillaries = flightCabinAncillaryRepository
        .findByFlightIdAndCabinClassId(flightId, cabinClassId);
    return flightCabinAncillaries.stream()
        .map(this::convertToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<FlightCabinAncillaryResponse> getAllByIds(List<Long> ids) {
    List<FlightCabinAncillary> flightCabinAncillaries = flightCabinAncillaryRepository.findAllById(ids);
    return flightCabinAncillaries.stream()
        .map(this::convertToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public FlightCabinAncillaryResponse getByFlightIdAndCabinClassIdAndType(Long flightId, Long cabinClassId,
      AncillaryType type) {

    FlightCabinAncillary flightCabinAncillary = flightCabinAncillaryRepository
        .findByFlightIdAndCabinClassIdAndAncillary_Type(flightId, cabinClassId, type);

    return convertToResponse(flightCabinAncillary);
  }

  @Override
  public List<FlightCabinAncillaryResponse> getAllByFlightIdAndCabinClassIdAndType(Long flightId, Long cabinClassId,
      AncillaryType type) {
    List<FlightCabinAncillary> flightCabinAncillaries = flightCabinAncillaryRepository
        .findAllByFlightIdAndCabinClassIdAndAncillary_Type(flightId, cabinClassId, type);

    return flightCabinAncillaries.stream()
        .map(this::convertToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public FlightCabinAncillaryResponse updateFlightCabinAncillary(Long id,
      FlightCabinAncillaryRequest flightCabinAncillaryRequest) throws Exception {

    FlightCabinAncillary flightCabinAncillary = flightCabinAncillaryRepository.findById(id)
        .orElseThrow(() -> new Exception("FlightCabinAncillary not found"));

    flightCabinAncillary.setAvailable(flightCabinAncillaryRequest.getAvailable());
    flightCabinAncillary.setMaxQuantity(flightCabinAncillaryRequest.getMaxQuantity());
    flightCabinAncillary.setPrice(flightCabinAncillaryRequest.getPrice());
    flightCabinAncillary.setIncludedInFare(flightCabinAncillaryRequest.getIncludedInFare());

    FlightCabinAncillary savedFlightCabinAncillary = flightCabinAncillaryRepository.save(flightCabinAncillary);

    return convertToResponse(savedFlightCabinAncillary);
  }

  @Override
  public void deleteFlightCabinAncillary(Long id) throws Exception {
    FlightCabinAncillary flightCabinAncillary = flightCabinAncillaryRepository.findById(id)
        .orElseThrow(() -> new Exception("FlightCabinAncillary not found"));
    flightCabinAncillaryRepository.delete(flightCabinAncillary);
  }

  @Override
  public Double calculateAncillaryPrice(List<Long> ancillaryIds) {
    List<FlightCabinAncillary> flightCabinAncillaries = flightCabinAncillaryRepository.findAllById(ancillaryIds);
    double totalPrice = 0.0;
    for (FlightCabinAncillary flightCabinAncillary : flightCabinAncillaries) {
      totalPrice += flightCabinAncillary.getPrice();
    }
    return totalPrice;
  }

  private FlightCabinAncillaryResponse convertToResponse(FlightCabinAncillary flightCabinAncillary) {
    List<InsuranceCoverage> coverages = insuranceCoverageRepository.findByAncillaryId(flightCabinAncillary.getId());
    List<InsuranceCoverageResponse> insuranceCoverageResponses = coverages.stream()
        .map(InsuranceCoverageMapper::toResponse)
        .collect(Collectors.toList());

    return FlightCabinAncillaryMapper.toResponse(flightCabinAncillary, insuranceCoverageResponses);
  }
}
