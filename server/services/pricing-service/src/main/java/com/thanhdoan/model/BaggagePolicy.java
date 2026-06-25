package com.thanhdoan.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class BaggagePolicy {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  @JsonIgnore
  private Fare fare;

  @Column(name = "name", nullable = false)
  private String name;

  private String description;

  // Cabin baggage
  private Double cabinBaggageMaxWeight;

  @Builder.Default
  private Integer cabinBaggagePieces = 1;

  private Double cabinBaggageWeightPerPiece;
  private Integer cabinBaggageMaxDimension;

  // Check-in baggage
  private Double checkInBaggageMaxWeight;

  @Builder.Default
  private Integer checkInBaggagePieces = 1;

  private Double checkInBaggageWeightPerPiece;

  // Benefits
  private Integer freeCheckedBagsAllowance;

  @Builder.Default
  private Boolean priorityBaggage = false;

  @Builder.Default
  private Boolean extraBaggageAllowance = false;

  private Long airlineId;

  @CreationTimestamp
  private Instant createdAt;

  @UpdateTimestamp
  private Instant updatedAt;

}
