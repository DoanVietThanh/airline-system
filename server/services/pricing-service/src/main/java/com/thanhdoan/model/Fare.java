package com.thanhdoan.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.thanhdoan.embeddable.BoardingBenefits;
import com.thanhdoan.embeddable.FlexibilityBenefits;
import com.thanhdoan.embeddable.InFlightBenefits;
import com.thanhdoan.embeddable.PremiumServiceBenefits;
import com.thanhdoan.embeddable.SeatBenefits;
import com.thanhdoan.enums.CabinClassType;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fares")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Fare {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "rbd_code", nullable = false)
  private String rbdCode;

  @Column(name = "flight_id", nullable = false)
  private Long flightId;

  @Column(name = "cabin_class_id", nullable = false)
  private Long cabinClassId;

  @Enumerated(EnumType.STRING)
  private CabinClassType cabinClass;

  @Column(name = "base_fare", nullable = false)
  private Double baseFare;

  private Double taxesAndFees;

  private Double airlineFees;

  @Column(name = "current_price", nullable = false)
  private Double currentPrice;

  private String fareLabel;

  @Embedded
  @Builder.Default
  private SeatBenefits seatBenefits = new SeatBenefits();

  @Embedded
  @Builder.Default
  private BoardingBenefits boardingBenefits = new BoardingBenefits();

  @Embedded
  @Builder.Default
  private InFlightBenefits inFlightBenefits = new InFlightBenefits();

  @Embedded
  @Builder.Default
  private FlexibilityBenefits flexibilityBenefits = new FlexibilityBenefits();

  @Embedded
  @Builder.Default
  private PremiumServiceBenefits premiumServiceBenefits = new PremiumServiceBenefits();

  @CreationTimestamp
  private Instant createdAt;

  @UpdateTimestamp
  private Instant updatedAt;

  public Double getTotalPrice() {
    return baseFare + taxesAndFees + airlineFees + currentPrice;
  }
}