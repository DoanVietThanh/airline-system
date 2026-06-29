package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.payload.request.AncillaryRequest;
import com.thanhdoan.payload.response.AncillaryResponse;

public interface AncillaryService {
  AncillaryResponse createAncillary(Long airlineId, AncillaryRequest ancillaryRequest);

  AncillaryResponse getAncillaryById(Long id) throws Exception;

  List<AncillaryResponse> getAncillariesByAirlineId(Long airlineId);

  AncillaryResponse updateAncillary(Long id, AncillaryRequest ancillaryRequest) throws Exception;

  void deleteAncillary(Long id) throws Exception;

}
