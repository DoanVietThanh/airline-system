package com.thanhdoan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanhdoan.repository.FareRepository;
import com.thanhdoan.repository.FareRulesRepository;
import com.thanhdoan.mapper.FareRulesMapper;
import com.thanhdoan.model.Fare;
import com.thanhdoan.model.FareRules;
import com.thanhdoan.payload.request.FareRulesRequest;
import com.thanhdoan.payload.response.FareRulesResponse;
import com.thanhdoan.service.FareRulesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FareRulesServiceImpl implements FareRulesService {

  private final FareRulesRepository fareRulesRepository;

  private final FareRepository fareRepository;

  @Override
  public FareRulesResponse createFareRules(FareRulesRequest fareRulesRequest) throws Exception {
    Fare fare = fareRepository.findById(fareRulesRequest.getFareId())
        .orElseThrow(() -> new Exception("Fare not found"));
    if (fareRulesRepository.existsByFareId(fareRulesRequest.getFareId())) {
      throw new Exception("Fare rules already exists");
    }
    FareRules fareRules = FareRulesMapper.toEntity(fareRulesRequest, fare);
    fareRulesRepository.save(fareRules);
    return FareRulesMapper.toResponse(fareRules);
  }

  @Override
  public FareRulesResponse getFareRulesById(Long id) throws Exception {
    FareRules fareRules = fareRulesRepository.findById(id)
        .orElseThrow(() -> new Exception("Fare rules not found"));
    return FareRulesMapper.toResponse(fareRules);
  }

  @Override
  public List<FareRulesResponse> getFareRulesByFareId(Long fareId) throws Exception {
    List<FareRules> fareRules = fareRulesRepository.findByFareId(fareId);
    return fareRules.stream()
        .map(FareRulesMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId) throws Exception {
    List<FareRules> fareRules = fareRulesRepository.findByAirlineId(airlineId);
    return fareRules.stream()
        .map(FareRulesMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public FareRulesResponse updateFareRules(Long id, FareRulesRequest fareRulesRequest) throws Exception {
    FareRules fareRules = fareRulesRepository.findById(id)
        .orElseThrow(() -> new Exception("Fare rules not found"));
    FareRulesMapper.updateEntity(fareRulesRequest, fareRules);
    fareRulesRepository.save(fareRules);
    return FareRulesMapper.toResponse(fareRules);
  }

  @Override
  public void deleteFareRules(Long id) throws Exception {
    FareRules fareRules = fareRulesRepository.findById(id)
        .orElseThrow(() -> new Exception("Fare rules not found"));
    fareRulesRepository.delete(fareRules);
  }

}
