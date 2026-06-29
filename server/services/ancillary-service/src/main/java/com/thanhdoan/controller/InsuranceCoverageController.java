package com.thanhdoan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.mapper.InsuranceCoverageMapper;
import com.thanhdoan.payload.request.InsuranceCoverageRequest;
import com.thanhdoan.payload.response.InsuranceCoverageResponse;
import com.thanhdoan.service.InsuranceCoverageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/insurance-coverages")
public class InsuranceCoverageController {
  private final InsuranceCoverageService insuranceCoverageService;

  @PostMapping
  public ResponseEntity<InsuranceCoverageResponse> createInsuranceCoverage(
      @Valid @RequestBody InsuranceCoverageRequest request) throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED).body(insuranceCoverageService.createInsuranceCoverage(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<InsuranceCoverageResponse> updateInsuranceCoverage(@PathVariable Long id,
      @Valid @RequestBody InsuranceCoverageRequest request) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(insuranceCoverageService.updateInsuranceCoverage(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteInsuranceCoverage(@PathVariable Long id) throws Exception {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<InsuranceCoverageResponse> getInsuranceCoverage(@PathVariable Long id) throws Exception {
    return ResponseEntity.status(HttpStatus.OK)
        .body(InsuranceCoverageMapper.toResponse(insuranceCoverageService.getCoverage(id)));
  }

  @GetMapping("/ancillary/{ancillaryId}")
  public ResponseEntity<List<InsuranceCoverageResponse>> getInsuranceCoveragesByAncillaryId(
      @PathVariable Long ancillaryId) throws Exception {
    return ResponseEntity.status(HttpStatus.OK)
        .body(insuranceCoverageService.getInsuranceCoveragesByAncillaryId(ancillaryId));
  }

  @GetMapping("/ancillary/{ancillaryId}/active")
  public ResponseEntity<List<InsuranceCoverageResponse>> getActiveInsuranceCoveragesByAncillaryId(
      @PathVariable Long ancillaryId) throws Exception {
    return ResponseEntity.status(HttpStatus.OK)
        .body(insuranceCoverageService.getActiveInsuranceCoveragesByAncillaryId(ancillaryId));
  }

  @GetMapping
  public ResponseEntity<List<InsuranceCoverageResponse>> getAllInsuranceCoverages() throws Exception {
    return ResponseEntity.status(HttpStatus.OK)
        .body(insuranceCoverageService.getAllInsuranceCoverages());
  }
}
