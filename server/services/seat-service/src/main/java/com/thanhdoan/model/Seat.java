package com.thanhdoan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.thanhdoan.enums.SeatType;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Seat {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String seatNumber;

  @Column(nullable = false)
  private Integer seatRow;

  private String columnLetter;

  private SeatType seatType;

  private Double basePrice;

  private Double premiumSuperCharge;

  @Builder.Default
  private Boolean isAvailable = true;

  @Builder.Default
  private Boolean isBlocked = false;

  @Builder.Default
  private Boolean isEmergencyExist = false;

  @Builder.Default
  private Boolean isActive = true;

  @Builder.Default
  private Boolean hasExtraLegroom = false;

  @Builder.Default
  private Boolean hasPowerOutlet = false;

  @Builder.Default
  private Boolean hasTvScreen = false;

  @Builder.Default
  private Boolean hasExtraWidth = false;

  @Builder.Default
  private Integer seatPitch = 0;

  @Builder.Default
  private Integer seatWidth = 0;

  @ManyToOne
  private SeatMap seatMap;

  @ManyToOne
  private CabinClass cabinClass;

  // createdAt, updatedAt, createdBy, updatedBy, version
  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  private LocalDateTime updatedAt;

  @CreatedBy
  @Column(updatable = false)
  private String createdBy;

  @LastModifiedBy
  private String updatedBy;

  @Version
  private Long version;

  public Double getTotalPrice() {
    Double total = basePrice != null ? basePrice : 0.0;
    if (premiumSuperCharge != null) {
      total += premiumSuperCharge;
    }
    return total;
  }

  public boolean isBookable() {
    return isAvailable && isActive && !isBlocked;
  }

  public String getFullPosition() {
    return seatRow + "" + columnLetter;
  }
}
