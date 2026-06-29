package com.thanhdoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhdoan.model.Ancillary;

public interface AncillaryRepository extends JpaRepository<Ancillary, Long> {

  List<Ancillary> findByAirlineId(Long airlineId);

}
