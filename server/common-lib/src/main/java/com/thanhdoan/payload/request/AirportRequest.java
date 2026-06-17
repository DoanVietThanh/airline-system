package com.thanhdoan.payload.request;

import java.time.ZoneId;

import com.thanhdoan.embeddable.Address;
import com.thanhdoan.embeddable.GeoCode;

import jakarta.validation.Valid;
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
public class AirportRequest {
  @NotBlank(message = "IATA code is required")
  @Size(min = 3, max = 3, message = "IATA code must be 3 characters long")
  private String iataCode;

  @NotBlank(message = "Name is required")
  private String name;

  private ZoneId timeZone;

  @Valid
  private Address address;

  @NotNull(message = "City ID is required")
  private Long cityId;

  @Valid
  private GeoCode geoCode;
}
