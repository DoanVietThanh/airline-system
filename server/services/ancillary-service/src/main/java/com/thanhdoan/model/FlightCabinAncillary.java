package com.thanhdoan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
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
public class FlightCabinAncillary {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private Long flightId;

  @Column(nullable = false)
  private Long cabinClassId;

  @ManyToOne
  private Ancillary ancillary;

  private Boolean available;

  private Integer maxQuantity;

  private Double price;

  @Builder.Default
  private Boolean includedInFare = false;
}
