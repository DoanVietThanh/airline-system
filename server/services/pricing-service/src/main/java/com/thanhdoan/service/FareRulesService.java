package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.payload.request.FareRulesRequest;
import com.thanhdoan.payload.response.FareRulesResponse;

public interface FareRulesService {

  FareRulesResponse createFareRules(FareRulesRequest fareRulesRequest) throws Exception;

  FareRulesResponse getFareRulesById(Long id) throws Exception;

  List<FareRulesResponse> getFareRulesByFareId(Long fareId) throws Exception;

  List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId) throws Exception;

  FareRulesResponse updateFareRules(Long id, FareRulesRequest fareRulesRequest) throws Exception;

  void deleteFareRules(Long id) throws Exception;
}