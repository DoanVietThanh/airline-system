package com.thanhdoan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanhdoan.mapper.InsuranceCoverageMapper;
import com.thanhdoan.model.Ancillary;
import com.thanhdoan.model.InsuranceCoverage;
import com.thanhdoan.payload.request.InsuranceCoverageRequest;
import com.thanhdoan.payload.response.InsuranceCoverageResponse;
import com.thanhdoan.repository.InsuranceCoverageRepository;
import com.thanhdoan.repository.AncillaryRepository;
import com.thanhdoan.service.InsuranceCoverageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuranceCoverageServiceImpl implements InsuranceCoverageService {

  private final InsuranceCoverageRepository insuranceCoverageRepository;
  private final AncillaryRepository ancillaryRepository;

  @Override
  public InsuranceCoverageResponse createInsuranceCoverage(InsuranceCoverageRequest request)
      throws Exception {
    if (request == null) {
      throw new Exception("Insurance coverage request cannot be null");
    }
    Ancillary ancillary = null;

    if (request.getAncillaryId() != null) {
      ancillary = ancillaryRepository.findById(request.getAncillaryId())
          .orElseThrow(() -> new Exception("Ancillary not found"));
    }
    InsuranceCoverage insuranceCoverage = InsuranceCoverageMapper.toEntity(request, ancillary);
    InsuranceCoverage savedInsuranceCoverage = insuranceCoverageRepository.save(insuranceCoverage);
    return InsuranceCoverageMapper.toResponse(savedInsuranceCoverage);
  }

  @Override
  public InsuranceCoverageResponse updateInsuranceCoverage(Long id, InsuranceCoverageRequest request)
      throws Exception {
    InsuranceCoverage insuranceCoverage = insuranceCoverageRepository.findById(id)
        .orElseThrow(() -> new Exception("Insurance coverage not found"));
    Ancillary ancillary = ancillaryRepository.findById(request.getAncillaryId())
        .orElseThrow(() -> new Exception("Ancillary not found"));
    InsuranceCoverage updatedInsuranceCoverage = InsuranceCoverageMapper.updateEntity(insuranceCoverage,
        request, ancillary);
    if (updatedInsuranceCoverage == null) {
      throw new Exception("Insurance coverage not found");
    }
    return InsuranceCoverageMapper.toResponse(insuranceCoverageRepository.save(updatedInsuranceCoverage));
  }

  @Override
  public void deleteInsuranceCoverage(Long id) throws Exception {
    InsuranceCoverage insuranceCoverage = insuranceCoverageRepository.findById(id)
        .orElseThrow(() -> new Exception("Insurance coverage not found"));
    insuranceCoverageRepository.delete(insuranceCoverage);
  }

  @Override
  public InsuranceCoverage getCoverage(Long id) throws Exception {
    InsuranceCoverage insuranceCoverage = insuranceCoverageRepository.findById(id)
        .orElseThrow(() -> new Exception("Insurance coverage not found"));
    return insuranceCoverage;
  }

  @Override
  public List<InsuranceCoverageResponse> getInsuranceCoveragesByAncillaryId(Long ancillaryId) {
    List<InsuranceCoverage> insuranceCoverages = insuranceCoverageRepository.findByAncillaryId(ancillaryId);
    return insuranceCoverages.stream()
        .map(InsuranceCoverageMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<InsuranceCoverageResponse> getActiveInsuranceCoveragesByAncillaryId(Long ancillaryId) {
    List<InsuranceCoverage> insuranceCoverages = insuranceCoverageRepository.findByAncillaryIdAndActive(ancillaryId,
        true);
    return insuranceCoverages.stream()
        .map(InsuranceCoverageMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<InsuranceCoverageResponse> getAllInsuranceCoverages() {
    List<InsuranceCoverage> insuranceCoverages = insuranceCoverageRepository.findAll();
    return insuranceCoverages.stream()
        .map(InsuranceCoverageMapper::toResponse)
        .collect(Collectors.toList());
  }

}
