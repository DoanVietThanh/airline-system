package com.thanhdoan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanhdoan.enums.CabinClassType;
import com.thanhdoan.mapper.CabinClassMapper;
import com.thanhdoan.model.CabinClass;
import com.thanhdoan.payload.request.CabinClassRequest;
import com.thanhdoan.payload.response.CabinClassResponse;
import com.thanhdoan.repository.CabinClassRepository;
import com.thanhdoan.service.CabinClassService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CabinClassServiceImpl implements CabinClassService {

  private final CabinClassRepository cabinClassRepository;

  @Override
  public CabinClassResponse createCabinClass(CabinClassRequest request) throws Exception {
    if (cabinClassRepository.existsByCodeAndAircraftId(request.getCode(), request.getAircraftId())) {
      throw new Exception("Cabin class code already exists");
    }
    CabinClass cabinClass = CabinClassMapper.toEntity(request);
    cabinClassRepository.save(cabinClass);
    return CabinClassMapper.toResponse(cabinClass, null);
  }

  @Override
  public CabinClassResponse getCabinClassById(Long id) throws Exception {
    CabinClass cabinClass = cabinClassRepository.findById(id)
        .orElseThrow(() -> new Exception("Cabin class not found"));
    return CabinClassMapper.toResponse(cabinClass, cabinClass.getSeatMap());
  }

  @Override
  public List<CabinClassResponse> getAllCabinClassesByAircraftId(Long aircraftId) {
    List<CabinClass> cabinClasses = cabinClassRepository.findByAircraftId(aircraftId);
    return cabinClasses.stream()
        .map(cabinClass -> CabinClassMapper.toResponse(cabinClass, cabinClass.getSeatMap()))
        .collect(Collectors.toList());
  }

  @Override
  public CabinClassResponse getCabinClassByAircraftIdAndName(Long aircraftId, CabinClassType name) {
    CabinClass cabinClass = cabinClassRepository.findByAircraftIdAndName(aircraftId, name);
    return CabinClassMapper.toResponse(cabinClass, null);
  }

  @Override
  public CabinClassResponse updateCabinClass(Long id, CabinClassRequest request) throws Exception {
    CabinClass cabinClass = cabinClassRepository.findById(id)
        .orElseThrow(() -> new Exception("Cabin class not found"));
    if (cabinClassRepository.existsByCodeAndAircraftIdNot(request.getCode(), request.getAircraftId(), id)) {
      throw new Exception("Cabin class code already exists");
    }
    CabinClassMapper.toUpdateEntity(request, cabinClass);
    CabinClass updatedCabinClass = cabinClassRepository.save(cabinClass);
    return CabinClassMapper.toResponse(updatedCabinClass, updatedCabinClass.getSeatMap());
  }

  @Override
  public void deleteCabinClass(Long id) throws Exception {
    CabinClass cabinClass = cabinClassRepository.findById(id)
        .orElseThrow(() -> new Exception("Cabin class not found"));
    cabinClassRepository.delete(cabinClass);
  }

}