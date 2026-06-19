package com.thanhdoan.payload.request;

import com.thanhdoan.enums.AirlineStatus;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirlineRequest {
  @NotBlank(message = "IATA code is required")
  @Size(min = 2, max = 2, message = "IATA code must be exactly 2 characters")
  private String iataCode;

  @NotBlank(message = "ICAO code is required")
  @Size(min = 3, max = 3, message = "ICAO code must be exactly 3 characters")
  private String icaoCode;

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Alias is required")
  private String alias;

  private String logoUrl;

  @NotBlank(message = "Website is required")
  private String website;

  private AirlineStatus status;

  private String alliance;

  private Long headquartersCityId;

  private String supportEmail;

  private String supportPhone;

  private String supportHours;

}
