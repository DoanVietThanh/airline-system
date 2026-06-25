package com.thanhdoan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.enums.CabinClassType;
import com.thanhdoan.service.CabinClassService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.thanhdoan.payload.request.CabinClassRequest;
import com.thanhdoan.payload.response.CabinClassResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cabin-classes")
public class CabinClassController {

  private final CabinClassService cabinClassService;

  @PostMapping
  public ResponseEntity<CabinClassResponse> createCabinClass(@Valid @RequestBody CabinClassRequest request)
      throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED).body(cabinClassService.createCabinClass(request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CabinClassResponse> getCabinClassById(@PathVariable Long id) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(cabinClassService.getCabinClassById(id));
  }

  @GetMapping("/aircraft/{aircraftId}")
  public ResponseEntity<List<CabinClassResponse>> getAllCabinClassesByAircraftId(@PathVariable Long aircraftId) {
    return ResponseEntity.status(HttpStatus.OK).body(cabinClassService.getAllCabinClassesByAircraftId(aircraftId));
  }

  @GetMapping("/aircraft/{aircraftId}/name/{cabinClass}")
  public ResponseEntity<CabinClassResponse> getCabinClassByAircraftIdAndName(@PathVariable Long aircraftId,
      @PathVariable CabinClassType cabinClass) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(cabinClassService.getCabinClassByAircraftIdAndName(aircraftId, cabinClass));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CabinClassResponse> updateCabinClass(@PathVariable Long id,
      @Valid @RequestBody CabinClassRequest request) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(cabinClassService.updateCabinClass(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCabinClass(@PathVariable Long id) throws Exception {
    cabinClassService.deleteCabinClass(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
