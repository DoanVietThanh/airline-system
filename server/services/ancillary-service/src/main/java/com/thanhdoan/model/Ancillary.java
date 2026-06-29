package com.thanhdoan.model;

import com.thanhdoan.domain.AncillaryMetadata;
import com.thanhdoan.enums.AncillaryType;
import com.thanhdoan.service.AncillaryMetadataConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ancillary {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Enumerated(EnumType.STRING)
  private AncillaryType type;

  // subType, rfisc, name, description, metadata
  private String subType;
  private String rfisc;

  @Column(nullable = false)
  private String name;

  private String description;

  @Convert(converter = AncillaryMetadataConverter.class)
  private AncillaryMetadata metadata;

  private Integer displayOrder;

  private Long airlineId;

}
