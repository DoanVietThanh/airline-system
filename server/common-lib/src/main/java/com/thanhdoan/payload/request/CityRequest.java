package com.thanhdoan.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityRequest {

  @NotBlank(message = "City name is required")
  @Size(max = 100, message = "City name must be less than 100 characters")
  private String name;

  @NotBlank(message = "City name is required")
  @Size(max = 10, message = "City code must be less than 10 characters")
  private String cityCode;

  @NotBlank(message = "Country name is required")
  @Size(max = 100, message = "Country name must be less than 100 characters")
  private String countryName;

  @NotBlank(message = "Country code is required")
  @Size(max = 10, message = "Country code must be less than 10 characters")
  private String countryCode;

  @Size(max = 10, message = "Region code must be less than 10 characters")
  private String regionCode;

  @Size(max = 10, message = "Time zone offset must be less than 10 characters")
  private String timeZoneOffset;

}
