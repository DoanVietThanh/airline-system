package com.thanhdoan.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thanhdoan.payload.request.CityRequest;
import com.thanhdoan.payload.response.CityResponse;

public interface CityService {
  CityResponse createCity(CityRequest cityRequest) throws Exception;

  CityResponse getCityById(Long id) throws Exception;

  CityResponse updateCity(Long id, CityRequest cityRequest) throws Exception;

  void deleteCity(Long id) throws Exception;

  Page<CityResponse> getAllCities(Pageable pageable);

  Page<CityResponse> searchCities(String keyword, Pageable pageable);

  Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable);

  boolean cityExists(String cityCode);

}
