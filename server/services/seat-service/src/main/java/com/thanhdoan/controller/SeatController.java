package com.thanhdoan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.request.SeatRequest;
import com.thanhdoan.payload.response.SeatResponse;
import com.thanhdoan.service.SeatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {
  private final SeatService seatService;

  @GetMapping
  public ResponseEntity<List<SeatResponse>> getAllSeats() {
    return ResponseEntity.ok(seatService.getAllSeats());
  }

  @PutMapping("/{id}")
  public ResponseEntity<SeatResponse> updateSeat(@PathVariable Long id, @RequestBody SeatRequest seatRequest) {
    return ResponseEntity.ok(seatService.updateSeat(id, seatRequest));
  }
}
