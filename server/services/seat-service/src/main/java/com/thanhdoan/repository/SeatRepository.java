package com.thanhdoan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhdoan.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
  boolean existsBySeatMapId(Long seatMapId);
}
