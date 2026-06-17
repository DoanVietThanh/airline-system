package com.thanhdoan.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.request.CityRequest;
import com.thanhdoan.payload.response.ApiResponse;
import com.thanhdoan.payload.response.CityResponse;
import com.thanhdoan.service.CityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {

  private final CityService cityService;

  // ---------- CREATE ----------

  @PostMapping
  public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest request)
      throws Exception {
    CityResponse response = cityService.createCity(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  // @PostMapping("/bulk")
  // public ResponseEntity<List<CityResponse>> createBulkCities(@Valid
  // @RequestBody List<CityRequest> requests)
  // throws Exception {
  // List<CityResponse> responses = cityService.createBulkCities(requests);
  // return ResponseEntity.status(HttpStatus.CREATED).body(responses);
  // }

  // ---------- READ ----------

  @GetMapping("/{id}")
  public ResponseEntity<CityResponse> getCityById(@PathVariable Long id)
      throws Exception {
    return ResponseEntity.ok(cityService.getCityById(id));
  }

  @GetMapping
  public ResponseEntity<Page<CityResponse>> getAllCities(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(defaultValue = "name") String sortBy,
      @RequestParam(defaultValue = "asc") String sortDirection) {

    Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);
    return ResponseEntity.ok(cityService.getAllCities(pageable));
  }

  // ---------- UPDATE ----------

  @PutMapping("/{id}")
  public ResponseEntity<CityResponse> updateCity(@PathVariable Long id, @Valid @RequestBody CityRequest request)
      throws Exception {
    return ResponseEntity.ok(cityService.updateCity(id, request));
  }

  // ---------- DELETE ----------

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteCity(@PathVariable Long id)
      throws Exception {
    cityService.deleteCity(id);
    return ResponseEntity.ok(new ApiResponse("City deleted successfully"));
  }

  // ---------- SEARCH & QUERY ----------

  @GetMapping("/search")
  public ResponseEntity<Page<CityResponse>> searchCities(
      @RequestParam String keyword,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size) {

    Pageable pageable = PageRequest.of(page, size);
    return ResponseEntity.ok(cityService.searchCities(keyword, pageable));
  }

  @GetMapping("/country/{countryCode}")
  public ResponseEntity<Page<CityResponse>> getCitiesByCountryCode(
      @PathVariable String countryCode,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size) {

    Pageable pageable = PageRequest.of(page, size);
    return ResponseEntity.ok(cityService.getCitiesByCountryCode(countryCode.toUpperCase(), pageable));
  }

  // ---------- VALIDATION ----------

  @GetMapping("/exists/{cityCode}")
  public ResponseEntity<Boolean> checkCityExists(@PathVariable String cityCode) {
    return ResponseEntity.ok(cityService.cityExists(cityCode.toUpperCase()));
  }
}
