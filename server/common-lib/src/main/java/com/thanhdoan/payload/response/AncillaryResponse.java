package com.thanhdoan.payload.response;

import java.util.List;

import com.thanhdoan.domain.AncillaryMetadata;
import com.thanhdoan.enums.AncillaryType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AncillaryResponse {
  private Long id;

  private AncillaryType type;
  private String subType;
  private String rfisc;
  private String name;
  private String description;
  private String categoryDisplayName;
  private String categoryIcon;
  private String iconUrl;
  private AncillaryMetadata metadata;
  private List<InsuranceCoverageResponse> converages;
  private Integer displayOrder;
  private Long airlineId;
}
