package com.thanhdoan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhdoan.model.SeatMap;

public interface SeatMapRepository extends JpaRepository<SeatMap, Long> {

  boolean existsByCabinClassId(Long cabinClassId);

  SeatMap findByCabinClassId(Long cabinClassId);

  boolean existsByAirlineIdAndCabinClassIdAndName(Long airlineId, Long cabinClassId, String name);

}
