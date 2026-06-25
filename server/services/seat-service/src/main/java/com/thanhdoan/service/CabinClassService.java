package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.enums.CabinClassType;
import com.thanhdoan.payload.request.CabinClassRequest;
import com.thanhdoan.payload.response.CabinClassResponse;

public interface CabinClassService {

  CabinClassResponse createCabinClass(CabinClassRequest request) throws Exception;

  CabinClassResponse getCabinClassById(Long id) throws Exception;

  List<CabinClassResponse> getAllCabinClassesByAircraftId(Long aircraftId);

  CabinClassResponse getCabinClassByAircraftIdAndName(Long aircraftId, CabinClassType name);

  CabinClassResponse updateCabinClass(Long id, CabinClassRequest request) throws Exception;

  void deleteCabinClass(Long id) throws Exception;
}
