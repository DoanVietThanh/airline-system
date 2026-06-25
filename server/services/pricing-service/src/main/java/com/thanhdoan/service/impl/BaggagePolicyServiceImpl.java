package com.thanhdoan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanhdoan.mapper.BaggagePolicyMapper;
import com.thanhdoan.model.BaggagePolicy;
import com.thanhdoan.model.Fare;
import com.thanhdoan.payload.request.BaggagePolicyRequest;
import com.thanhdoan.payload.response.BaggagePolicyResponse;
import com.thanhdoan.repository.BaggagePolicyRepository;
import com.thanhdoan.repository.FareRepository;
import com.thanhdoan.service.BaggagePolicyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaggagePolicyServiceImpl implements BaggagePolicyService {

  private final FareRepository fareRepository;
  private final BaggagePolicyRepository baggagePolicyRepository;

  @Override
  public BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest baggagePolicyRequest) throws Exception {
    Fare fare = fareRepository.findById(baggagePolicyRequest.getFareId())
        .orElseThrow(() -> new Exception("Fare not found"));
    if (baggagePolicyRepository.existsByFareId(baggagePolicyRequest.getFareId())) {
      throw new Exception("Baggage policy already exists");
    }
    BaggagePolicy baggagePolicy = BaggagePolicyMapper.toEntity(baggagePolicyRequest, fare);
    baggagePolicy = baggagePolicyRepository.save(baggagePolicy);
    return BaggagePolicyMapper.toResponse(baggagePolicy);
  }

  @Override
  public BaggagePolicyResponse getBaggagePolicyById(Long id) throws Exception {
    BaggagePolicy baggagePolicy = baggagePolicyRepository.findById(id)
        .orElseThrow(() -> new Exception("Baggage policy not found"));
    return BaggagePolicyMapper.toResponse(baggagePolicy);
  }

  @Override
  public BaggagePolicyResponse getBaggagePolicyByFareId(Long fareId) {
    BaggagePolicy baggagePolicy = baggagePolicyRepository.findByFareId(fareId);
    return BaggagePolicyMapper.toResponse(baggagePolicy);
  }

  @Override
  public List<BaggagePolicyResponse> getBaggagePoliciesByAirlineId(Long airlineId) {
    List<BaggagePolicy> baggagePolicies = baggagePolicyRepository.findByAirlineId(airlineId);
    return baggagePolicies.stream()
        .map(BaggagePolicyMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public BaggagePolicyResponse updateBaggagePolicy(Long id, BaggagePolicyRequest baggagePolicyRequest)
      throws Exception {
    BaggagePolicy baggagePolicy = baggagePolicyRepository.findById(id)
        .orElseThrow(() -> new Exception("Baggage policy not found"));
    BaggagePolicyMapper.toUpdateEntity(baggagePolicyRequest, baggagePolicy);
    BaggagePolicy updatedBaggagePolicy = baggagePolicyRepository.save(baggagePolicy);
    return BaggagePolicyMapper.toResponse(updatedBaggagePolicy);
  }

  @Override
  public void deleteBaggagePolicy(Long id) throws Exception {
    BaggagePolicy baggagePolicy = baggagePolicyRepository.findById(id)
        .orElseThrow(() -> new Exception("Baggage policy not found"));
    baggagePolicyRepository.delete(baggagePolicy);
  }

}
