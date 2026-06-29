package com.thanhdoan.model;

import com.thanhdoan.enums.CoverageType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InsuranceCoverage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Ancillary ancillary;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CoverageType coverageType;

  @Column(nullable = false, length = 200)
  private String name;

  @Column(length = 1000)
  private String description;

  @Column(nullable = false)
  private Double coverageAmount;

  // isFlat, claimCondition, displayOrder, active
  @Builder.Default
  private Boolean isFlat = true;

  private String claimCondition;

  private Integer displayOrder;

  private String emergencyContact;

  @Builder.Default
  private Boolean active = true;
}
