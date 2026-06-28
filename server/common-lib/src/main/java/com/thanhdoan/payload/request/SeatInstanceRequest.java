package com.thanhdoan.payload.request;

import java.time.LocalDateTime;

import com.thanhdoan.enums.SeatType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatInstanceRequest {
  private String seatNumber;
  private Integer seatRow;
  private String columnLetter;
  private SeatType seatType;

  private Boolean isAvailable;
  private Boolean isBlocked;
  private Boolean isEmergencyExist;
  private Boolean isActive;

  private Double basePrice;
  private Double premiumSuperCharge;
  private Double totalPrice;

  private Boolean hasExtraLegroom;
  private Boolean hasPowerOutlet;
  private Boolean hasTvScreen;
  private Boolean hasExtraWidth;

  private Integer seatPitch;
  private Integer seatWidth;

  private Long seatMapId;
  private Long cabinClassId;
  private String seatMapName;
  private String cabinClassName;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String createdBy;
  private String updatedBy;

  private Boolean isPremiumSeat;
  private Boolean isBookable;
  private String fullPosition;
  private String seatCharacteristics;
}
