package com.thanhdoan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.thanhdoan.enums.AncillaryType;
import com.thanhdoan.model.FlightCabinAncillary;

public interface FlightCabinAncillaryRepository extends JpaRepository<FlightCabinAncillary, Long> {
  List<FlightCabinAncillary> findByFlightIdAndCabinClassId(Long flightId, Long cabinClassId);

  FlightCabinAncillary findByFlightIdAndCabinClassIdAndAncillary_Type(Long flightId, Long cabinClassId,
      AncillaryType type);

  List<FlightCabinAncillary> findAllByFlightIdAndCabinClassIdAndAncillary_Type(Long flightId, Long cabinClassId,
      AncillaryType type);

}
