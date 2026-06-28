package com.thanhdoan.payload.response;

import java.time.Instant;

import com.thanhdoan.enums.CabinClassType;
import com.thanhdoan.enums.SeatAvailableStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatInstanceResponse {

  private Long id;

  private Long flightId;
  private Long seatId;
  private String seatNumber;
  private String seatType;
  private String seatPosition;

  private SeatResponse seat;

  private Double price;

  private SeatAvailableStatus status;
  private Long flightInstanceId;
  private Boolean isBooked;

  private Long flightCabinId;
  private CabinClassType flightCabinClassType;
  private String mealPreference;
  private Double fare;

  private Long version;
  private Instant createdAt;
  private Instant updatedAt;

  private Boolean isAvailable;
  private Boolean isOccupied;
  private String seatCharacteristics;

}
