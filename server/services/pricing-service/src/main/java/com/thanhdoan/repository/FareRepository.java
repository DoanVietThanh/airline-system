package com.thanhdoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhdoan.model.Fare;

public interface FareRepository extends JpaRepository<Fare, Long> {

  boolean existsByFlightIdAndCabinClassIdAndName(Long flightId, Long cabinClassId, String name);

  List<Fare> findByFlightIdAndCabinClassId(Long flightId, Long cabinClassId);

  List<Fare> findByFlightIdInAndCabinClassId(List<Long> flightIds, Long cabinClassId);

  boolean existsByFlightIdAndCabinClassIdAndNameAndIdNot(Long flightId, Long cabinClassId, String name, Long id);
}
