package com.thanhdoan.service.impl;

import org.springframework.stereotype.Service;

import com.thanhdoan.mapper.SeatMapMapper;
import com.thanhdoan.model.CabinClass;
import com.thanhdoan.model.SeatMap;
import com.thanhdoan.payload.request.SeatMapRequest;
import com.thanhdoan.payload.response.SeatMapResponse;
import com.thanhdoan.repository.CabinClassRepository;
import com.thanhdoan.repository.SeatMapRepository;
import com.thanhdoan.service.SeatMapService;
import com.thanhdoan.service.SeatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatMapServiceImpl implements SeatMapService {

  private final SeatMapRepository seatMapRepository;
  private final CabinClassRepository cabinClassRepository;
  private final SeatService seatService;

  @Override
  public SeatMapResponse createSeatMap(Long airlineId, SeatMapRequest request) throws Exception {
    if (seatMapRepository.existsByAirlineIdAndCabinClassIdAndName(airlineId, request.getCabinClassId(),
        request.getName())) {
      throw new Exception("Seat map already exists");
    }
    CabinClass cabinClass = cabinClassRepository.findById(request.getCabinClassId())
        .orElseThrow(() -> new Exception("Cabin class not found"));
    SeatMap seatMap = SeatMapMapper.toEntity(request, cabinClass);
    SeatMap savedSeatMap = seatMapRepository.save(seatMap);
    seatService.generateSeats(savedSeatMap.getId());
    return SeatMapMapper.toResponse(savedSeatMap);
  }

  @Override
  public SeatMapResponse getSeatMapById(Long id) throws Exception {
    SeatMap seatMap = seatMapRepository.findById(id)
        .orElseThrow(() -> new Exception("Seat map not found"));
    return SeatMapMapper.toResponse(seatMap);
  }

  @Override
  public SeatMapResponse getSeatMapByCabinClassId(Long cabinClassId) throws Exception {
    SeatMap seatMap = seatMapRepository.findByCabinClassId(cabinClassId);
    return SeatMapMapper.toResponse(seatMap);
  }

  @Override
  public SeatMapResponse updateSeatMap(Long id, SeatMapRequest request) throws Exception {
    SeatMap seatMap = seatMapRepository.findById(id)
        .orElseThrow(() -> new Exception("Seat map not found"));
    SeatMapMapper.updateEntity(seatMap, request);
    seatMapRepository.save(seatMap);
    return SeatMapMapper.toResponse(seatMap);
  }

  @Override
  public void deleteSeatMap(Long id) throws Exception {
    SeatMap seatMap = seatMapRepository.findById(id)
        .orElseThrow(() -> new Exception("Seat map not found"));
    seatMapRepository.delete(seatMap);
  }

}
