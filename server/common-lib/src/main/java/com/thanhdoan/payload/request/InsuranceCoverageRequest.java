package com.thanhdoan.payload.request;

import com.thanhdoan.enums.CoverageType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceCoverageRequest {

  @NotNull(message = "Ancillary ID is required")
  private Long ancillaryId;

  @NotNull(message = "Coverage type is required")
  private CoverageType coverageType;

  @NotBlank(message = "Name is required")
  @Size(max = 200, message = "Name must be less than 200 characters")
  private String name;

  @NotBlank(message = "Description is required")
  @Size(max = 1000, message = "Description must be less than 1000 characters")
  private String description;

  @NotNull(message = "Coverage amount is required")
  private Double coverageAmount;

  @NotNull(message = "Is flat is required")
  private Boolean isFlat;

  @NotBlank(message = "Claim condition is required")
  @Size(max = 1000, message = "Claim condition must be less than 1000 characters")
  private String claimCondition;

  @Size(max = 100, message = "Emergency contact must be less than 100 characters")
  private String emergencyContact;

  private Integer displayOrder;

  private Boolean active;

}
