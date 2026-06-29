package com.thanhdoan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.thanhdoan.model.InsuranceCoverage;

public interface InsuranceCoverageRepository extends JpaRepository<InsuranceCoverage, Long> {

  List<InsuranceCoverage> findByAncillaryId(Long ancillaryId);

  List<InsuranceCoverage> findByAncillaryIdAndActiveTrue(Long ancillaryId);

  List<InsuranceCoverage> findByAncillaryIdAndActive(Long ancillaryId, Boolean active);

  List<InsuranceCoverage> findAll();
}