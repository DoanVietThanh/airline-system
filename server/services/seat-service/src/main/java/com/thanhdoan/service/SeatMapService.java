package com.thanhdoan.service;

import com.thanhdoan.payload.request.SeatMapRequest;
import com.thanhdoan.payload.response.SeatMapResponse;

public interface SeatMapService {

  SeatMapResponse createSeatMap(Long airlineId, SeatMapRequest request) throws Exception;

  SeatMapResponse getSeatMapById(Long id) throws Exception;

  SeatMapResponse getSeatMapByCabinClassId(Long cabinClassId) throws Exception;

  SeatMapResponse updateSeatMap(Long id, SeatMapRequest request) throws Exception;

  void deleteSeatMap(Long id) throws Exception;

}
