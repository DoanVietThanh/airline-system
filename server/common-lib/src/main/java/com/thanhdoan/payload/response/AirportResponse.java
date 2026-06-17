package com.thanhdoan.payload.response;

import java.time.ZoneId;

import com.thanhdoan.embeddable.Address;
import com.thanhdoan.embeddable.GeoCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportResponse {
  private Long id;
  private String iataCode;
  private String name;
  private String detailedName;
  private ZoneId timeZone;
  private Address address;
  private CityResponse city;
  private GeoCode geoCode;

}
