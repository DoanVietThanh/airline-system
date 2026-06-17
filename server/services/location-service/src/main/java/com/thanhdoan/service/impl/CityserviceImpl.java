package com.thanhdoan.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thanhdoan.mapper.CityMapper;
import com.thanhdoan.model.City;
import com.thanhdoan.payload.request.CityRequest;
import com.thanhdoan.payload.response.CityResponse;
import com.thanhdoan.repository.CityRepository;
import com.thanhdoan.service.CityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityserviceImpl implements CityService {

  private final CityRepository cityRepository;

  @Override
  public CityResponse createCity(CityRequest cityRequest) throws Exception {
    if (cityRepository.existsByCityCode(cityRequest.getCityCode())) {
      throw new Exception("City code already exists");
    }

    City city = CityMapper.toEntity(cityRequest);
    City result = cityRepository.save(city);
    return CityMapper.toResponse(result);
  }

  @Override
  public CityResponse getCityById(Long id) throws Exception {
    City city = cityRepository.findById(id)
        .orElseThrow(() -> new Exception("City not found"));
    return CityMapper.toResponse(city);
  }

  @Override
  public CityResponse updateCity(Long id, CityRequest cityRequest) throws Exception {
    City city = cityRepository.findById(id)
        .orElseThrow(() -> new Exception("City not found"));

    if (cityRepository.existsByCityCodeAndIdNot(cityRequest.getCityCode(), id)) {
      throw new Exception("City code already exists");
    }

    City updatedCity = cityRepository.save(CityMapper.updateEntity(city, cityRequest));

    return CityMapper.toResponse(updatedCity);
  }

  @Override
  public void deleteCity(Long id) throws Exception {
    City city = cityRepository.findById(id)
        .orElseThrow(() -> new Exception("City not found"));
    cityRepository.delete(city);
  }

  @Override
  public Page<CityResponse> getAllCities(Pageable pageable) {
    Page<City> cities = cityRepository.findAll(pageable);
    return cities.map(CityMapper::toResponse);
  }

  @Override
  public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
    Page<City> cities = cityRepository.searchByKeyword(keyword, pageable);
    return cities.map(CityMapper::toResponse);
  }

  @Override
  public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
    Page<City> cities = cityRepository.findByCountryCodeIgnoreCase(countryCode, pageable);
    return cities.map(CityMapper::toResponse);
  }

  @Override
  public boolean cityExists(String cityCode) {
    return cityRepository.existsByCityCode(cityCode);
  }

}
