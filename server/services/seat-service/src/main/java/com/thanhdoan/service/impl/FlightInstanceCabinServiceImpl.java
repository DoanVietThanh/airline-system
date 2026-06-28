package com.thanhdoan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thanhdoan.enums.SeatAvailableStatus;
import com.thanhdoan.enums.SeatType;
import com.thanhdoan.mapper.FlightInstanceCabinMapper;
import com.thanhdoan.model.CabinClass;
import com.thanhdoan.model.FlightInstanceCabin;
import com.thanhdoan.model.SeatInstance;
import com.thanhdoan.model.SeatMap;
import com.thanhdoan.payload.request.FlightInstanceCabinRequest;
import com.thanhdoan.payload.response.FlightInstanceCabinResponse;
import com.thanhdoan.repository.CabinClassRepository;
import com.thanhdoan.repository.FlightInstanceCabinRepository;
import com.thanhdoan.repository.SeatInstanceRepository;
import com.thanhdoan.repository.SeatMapRepository;
import com.thanhdoan.service.FlightInstanceCabinService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightInstanceCabinServiceImpl implements FlightInstanceCabinService {

  private final FlightInstanceCabinRepository flightInstanceCabinRepository;
  private final CabinClassRepository cabinClassRepository;
  private final SeatMapRepository seatMapRepository;
  private final SeatInstanceRepository seatInstanceRepository;

  @Override
  public FlightInstanceCabinResponse createFlightInstanceCabin(FlightInstanceCabinRequest request) {
    CabinClass cabinClass = cabinClassRepository.findById(request.getCabinClassId())
        .orElseThrow(() -> new EntityNotFoundException("Cabin class not found"));
    SeatMap seatMap = seatMapRepository.findByCabinClassId(cabinClass.getId());
    if (seatMap == null) {
      throw new EntityNotFoundException("Seat map not found");
    }
    if (seatMap.getSeats() == null || seatMap.getSeats().isEmpty()) {
      throw new EntityNotFoundException("Seats not found");
    }
    int totalSeats = seatMap.getSeats().size();
    FlightInstanceCabin flightInstanceCabin = FlightInstanceCabin.builder()
        .flightInstanceId(request.getFlightInstanceId())
        .cabinClass(cabinClass)
        .totalSeats(totalSeats)
        .bookedSeats(0)
        .build();

    FlightInstanceCabin savedFlightInstanceCabin = flightInstanceCabinRepository.save(flightInstanceCabin);

    // TODO: generate seat instances
    List<SeatInstance> seatInstances = seatMap.getSeats().stream().map(seat -> {
      Double premiumSupercharge = getPremiumSupercharge(seat.getSeatType(), 1000.0,
          500.0);
      SeatInstance seatInstance = SeatInstance.builder()
          .flightId(request.getFlightInstanceId())
          .status(SeatAvailableStatus.AVAILABLE)
          .flightInstanceId(request.getFlightInstanceId())
          .flightInstanceCabin(savedFlightInstanceCabin)
          .seat(seat)
          .isAvailable(true)
          .isBooked(false)
          .premiumSupercharge(premiumSupercharge)
          .build();
      return seatInstance;
    }).collect(Collectors.toList());
    seatInstanceRepository.saveAll(seatInstances);
    savedFlightInstanceCabin.setSeats(seatInstances);
    return FlightInstanceCabinMapper.toResponse(savedFlightInstanceCabin);
  }

  @Override
  public FlightInstanceCabinResponse getFlightInstanceCabinById(Long id) {
    FlightInstanceCabin flightInstanceCabin = flightInstanceCabinRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Flight instance cabin not found"));
    return FlightInstanceCabinMapper.toResponse(flightInstanceCabin);
  }

  @Override
  public Page<FlightInstanceCabinResponse> getByFlightInstanceId(Long flightInstanceId, Pageable pageable) {
    Page<FlightInstanceCabin> flightInstanceCabins = flightInstanceCabinRepository
        .findByFlightInstanceId(flightInstanceId, pageable);
    return flightInstanceCabins.map(FlightInstanceCabinMapper::toResponse);
  }

  @Override
  public FlightInstanceCabinResponse getByFlightInstanceIdAndCabinClassId(Long flightInstanceId, Long cabinClassId) {
    FlightInstanceCabin flightInstanceCabin = flightInstanceCabinRepository
        .findByFlightInstanceIdAndCabinClassId(flightInstanceId, cabinClassId);
    return FlightInstanceCabinMapper.toResponse(flightInstanceCabin);
  }

  @Override
  public FlightInstanceCabinResponse updateFlightInstanceCabin(Long id, FlightInstanceCabinRequest request) {
    FlightInstanceCabin flightInstanceCabin = flightInstanceCabinRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Flight instance cabin not found"));

    if (request.getCabinClassId() != null) {
      CabinClass cabinClass = cabinClassRepository.findById(request.getCabinClassId())
          .orElseThrow(() -> new EntityNotFoundException("Cabin class not found"));
      flightInstanceCabin.setCabinClass(cabinClass);
    }
    FlightInstanceCabin savedFlightInstanceCabin = flightInstanceCabinRepository.save(flightInstanceCabin);
    return FlightInstanceCabinMapper.toResponse(savedFlightInstanceCabin);
  }

  @Override
  public void deleteFlightInstanceCabin(Long id) {
    FlightInstanceCabin flightInstanceCabin = flightInstanceCabinRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Flight instance cabin not found"));
    flightInstanceCabinRepository.delete(flightInstanceCabin);
  }

  private Double getPremiumSupercharge(SeatType seatType, Double windowSuperCharge, Double aisleSuperCharge) {
    if (seatType == null) {
      return 0.0;
    }
    switch (seatType) {
      case WINDOW:
        return windowSuperCharge;
      case AISLE:
        return aisleSuperCharge;
      default:
        return 0.0;
    }
  }

}
