package com.thanhdoan.service;

import com.thanhdoan.payload.request.CityRequest;
import com.thanhdoan.payload.response.CityResponse;

public interface CityService {
  CityResponse createCity(CityRequest cityRequest);
}
