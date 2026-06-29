package com.thanhdoan.payload.request;

import com.thanhdoan.domain.AncillaryMetadata;
import com.thanhdoan.enums.AncillaryType;

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
public class AncillaryRequest {

  @NotNull(message = "Type is required")
  private AncillaryType type;

  @Size(max = 100, message = "SubType must be less than 100 characters")
  private String subType;

  @Size(max = 10, message = "RFISC must be less than 10 characters")
  private String rfisc;

  @NotBlank(message = "Name is required")
  @Size(max = 200, message = "Name must be less than 200 characters")
  private String name;

  @Size(max = 1000, message = "Description must be less than 1000 characters")
  private String description;

  private String iconUrl;

  @NotNull(message = "Metadata is required")
  private AncillaryMetadata metadata;

  private Integer displayOrder;

}
