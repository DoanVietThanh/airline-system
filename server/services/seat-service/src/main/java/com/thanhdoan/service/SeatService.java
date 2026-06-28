package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.payload.request.SeatRequest;
import com.thanhdoan.payload.response.SeatResponse;

public interface SeatService {
  void generateSeats(Long seatMapId) throws Exception;

  List<SeatResponse> getAllSeats();

  SeatResponse updateSeat(Long seatId, SeatRequest seatRequest);
}
