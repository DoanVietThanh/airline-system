package com.thanhdoan.service;

import java.util.List;
import java.util.Map;

import com.thanhdoan.model.Fare;
import com.thanhdoan.payload.request.FareRequest;
import com.thanhdoan.payload.response.FareResponse;

public interface FareService {

  // createFare, getFareById, getFaresByFlightIdAndCabinCLassId, updateFare,
  // deleteFare, getFares, getLowestFarePerFlight, getFaresByIds
  FareResponse createFare(FareRequest fareRequest) throws Exception;

  FareResponse getFareById(Long id) throws Exception;

  List<FareResponse> getFaresByFlightIdAndCabinCLassId(Long flightId, Long cabinClassId);

  FareResponse updateFare(Long id, FareRequest fareRequest) throws Exception;

  void deleteFare(Long id) throws Exception;

  List<Fare> getFares();

  Map<Long, FareResponse> getLowestFarePerFlight(List<Long> flightIds, Long cabinClassId) throws Exception;

  Map<Long, FareResponse> getFaresByIds(List<Long> ids);
}
