package com.thanhdoan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanhdoan.enums.SeatType;
import com.thanhdoan.mapper.SeatMapper;
import com.thanhdoan.model.Seat;
import com.thanhdoan.model.SeatMap;
import com.thanhdoan.payload.request.SeatRequest;
import com.thanhdoan.payload.response.SeatResponse;
import com.thanhdoan.repository.SeatMapRepository;
import com.thanhdoan.repository.SeatRepository;
import com.thanhdoan.service.SeatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

  private final SeatRepository seatRepository;
  private final SeatMapRepository seatMapRepository;

  @Override
  public void generateSeats(Long seatMapId) throws Exception {
    if (seatRepository.existsBySeatMapId(seatMapId)) {
      throw new Exception("Seats already exist for this seat map");
    }

    SeatMap seatMap = seatMapRepository.findById(seatMapId)
        .orElseThrow(() -> new Exception("Seat map not found"));

    int leftSeatsPerRow = seatMap.getLeftSeatsPerRow();
    int rightSeatsPerRow = seatMap.getRightSeatsPerRow();
    int rows = seatMap.getTotalRows();
    int seatsPerRow = leftSeatsPerRow + rightSeatsPerRow;

    List<Seat> seats = new ArrayList<>();
    for (int row = 1; row <= rows; row++) {
      for (int col = 0; col < seatsPerRow; col++) {
        String seatNum = row + getSeatLetter(col);
        SeatType type = getSeatType(col, leftSeatsPerRow, rightSeatsPerRow);

        Seat seat = Seat.builder()
            .seatNumber(seatNum)
            .seatRow(row)
            .seatType(type)
            .columnLetter(getSeatLetter(col))
            .seatMap(seatMap)
            .build();
        seats.add(seat);
      }
    }
    seatRepository.saveAll(seats);
  }

  private SeatType getSeatType(int col, int leftSeatsPerRow, int rightSeatsPerRow) {
    int totalSeats = leftSeatsPerRow + rightSeatsPerRow;
    if (col == 0 || col == totalSeats - 1) {
      return SeatType.WINDOW;
    }
    if (col == leftSeatsPerRow - 1) {
      return SeatType.AISLE;
    }
    if (col == leftSeatsPerRow) {
      return SeatType.AISLE;
    }
    return SeatType.MIDDLE;
  }

  private String getSeatLetter(int col) {
    StringBuilder sb = new StringBuilder();
    while (col >= 0) {
      sb.insert(0, (char) ('A' + (col % 26)));
      col = (col / 26) - 1;
    }
    return sb.toString();
  }

  @Override
  public List<SeatResponse> getAllSeats() {
    List<Seat> seats = seatRepository.findAll();
    return seats.stream().map(SeatMapper::toResponse).collect(Collectors.toList());
  }

  @Override
  public SeatResponse updateSeat(Long seatId, SeatRequest seatRequest) {
    Seat seat = seatRepository.findById(seatId)
        .orElseThrow(() -> new RuntimeException("Seat not found"));
    Seat updatedSeat = SeatMapper.updateEntity(seat, seatRequest);
    return SeatMapper.toResponse(seatRepository.save(updatedSeat));
  }

}
