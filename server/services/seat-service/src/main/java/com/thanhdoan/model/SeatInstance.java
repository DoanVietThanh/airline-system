package com.thanhdoan.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.thanhdoan.enums.SeatAvailableStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SeatInstance {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private Long flightId;

  @ManyToOne
  private FlightInstanceCabin flightInstanceCabin;

  private Long flightInstanceId;

  @ManyToOne
  private Seat seat;

  @Builder.Default
  private SeatAvailableStatus status = SeatAvailableStatus.AVAILABLE;

  private boolean isBooked;
  private boolean isAvailable;

  private Double fare;
  private Double premiumSupercharge;

  private Long flightScheduleId;

  @Version
  private Long version;

  @CreatedDate
  private Instant createdAt;

  @LastModifiedDate
  private Instant updatedAt;
}
