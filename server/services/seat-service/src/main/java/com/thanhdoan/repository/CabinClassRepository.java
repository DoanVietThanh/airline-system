package com.thanhdoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhdoan.enums.CabinClassType;
import com.thanhdoan.model.CabinClass;

public interface CabinClassRepository extends JpaRepository<CabinClass, Long> {

  List<CabinClass> findByAircraftId(Long aircraftId);

  CabinClass findByAircraftIdAndName(Long aircraftId, CabinClassType name);

  boolean existsByCodeAndAircraftId(String code, Long aircraftId);

  boolean existsByCodeAndAircraftIdNot(String code, Long aircraftId, Long id);
}
