package com.thanhdoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhdoan.model.BaggagePolicy;

public interface BaggagePolicyRepository extends JpaRepository<BaggagePolicy, Long> {

  BaggagePolicy findByFareId(Long fareId);

  List<BaggagePolicy> findByAirlineId(Long airlineId);

  boolean existsByFareId(Long fareId);
}
