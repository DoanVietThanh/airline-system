package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.model.InsuranceCoverage;
import com.thanhdoan.payload.request.InsuranceCoverageRequest;
import com.thanhdoan.payload.response.InsuranceCoverageResponse;

public interface InsuranceCoverageService {
  InsuranceCoverageResponse createInsuranceCoverage(InsuranceCoverageRequest insuranceCoverageRequest) throws Exception;

  InsuranceCoverageResponse updateInsuranceCoverage(Long id, InsuranceCoverageRequest insuranceCoverageRequest) throws Exception;

  void deleteInsuranceCoverage(Long id) throws Exception;

  InsuranceCoverage getCoverage(Long id) throws Exception;

  List<InsuranceCoverageResponse> getInsuranceCoveragesByAncillaryId(Long ancillaryId);

  List<InsuranceCoverageResponse> getActiveInsuranceCoveragesByAncillaryId(Long ancillaryId);

  List<InsuranceCoverageResponse> getAllInsuranceCoverages();

}
