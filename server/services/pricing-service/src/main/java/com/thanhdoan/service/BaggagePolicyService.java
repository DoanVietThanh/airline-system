package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.payload.request.BaggagePolicyRequest;
import com.thanhdoan.payload.response.BaggagePolicyResponse;

public interface BaggagePolicyService {
  BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest baggagePolicyRequest) throws Exception;

  BaggagePolicyResponse getBaggagePolicyById(Long id) throws Exception;

  BaggagePolicyResponse getBaggagePolicyByFareId(Long fareId);

  List<BaggagePolicyResponse> getBaggagePoliciesByAirlineId(Long airlineId);

  BaggagePolicyResponse updateBaggagePolicy(Long id, BaggagePolicyRequest baggagePolicyRequest) throws Exception;

  void deleteBaggagePolicy(Long id) throws Exception;
}
