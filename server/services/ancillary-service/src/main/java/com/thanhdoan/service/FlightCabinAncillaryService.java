package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.enums.AncillaryType;
import com.thanhdoan.payload.request.FlightCabinAncillaryRequest;
import com.thanhdoan.payload.response.FlightCabinAncillaryResponse;

public interface FlightCabinAncillaryService {
    FlightCabinAncillaryResponse createFlightCabinAncillary(FlightCabinAncillaryRequest flightCabinAncillaryRequest)
            throws Exception;

    FlightCabinAncillaryResponse getFlightCabinAncillaryById(Long id) throws Exception;

    List<FlightCabinAncillaryResponse> getByFlightIdAndCabinClassId(Long flightId, Long cabinClassId);

    List<FlightCabinAncillaryResponse> getAllByIds(List<Long> ids);

    FlightCabinAncillaryResponse getByFlightIdAndCabinClassIdAndType(Long flightId, Long cabinClassId,
            AncillaryType type);

    List<FlightCabinAncillaryResponse> getAllByFlightIdAndCabinClassIdAndType(Long flightId, Long cabinClassId,
            AncillaryType type);

    FlightCabinAncillaryResponse updateFlightCabinAncillary(Long id,
            FlightCabinAncillaryRequest flightCabinAncillaryRequest) throws Exception;

    void deleteFlightCabinAncillary(Long id) throws Exception;

    Double calculateAncillaryPrice(List<Long> ancillaryIds);

}
