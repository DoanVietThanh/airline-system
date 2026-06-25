package com.thanhdoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhdoan.model.FareRules;

public interface FareRulesRepository extends JpaRepository<FareRules, Long> {

  List<FareRules> findByFareId(Long fareId);

  List<FareRules> findByAirlineId(Long airlineId);

  boolean existsByFareId(Long fareId);

  boolean existsByFareIdAndAirlineId(Long fareId, Long airlineId);

}
