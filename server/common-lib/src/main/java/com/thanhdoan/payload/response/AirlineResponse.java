package com.thanhdoan.payload.response;

import java.time.Instant;
import java.time.LocalDateTime;

import com.thanhdoan.embeddable.Support;
import com.thanhdoan.enums.AirlineStatus;
import com.thanhdoan.payload.dto.UserDto;

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
public class AirlineResponse {
  private Long id;
  private String iataCode;
  private String icaoCode;
  private String name;
  private String alias;

  private String logoUrl;
  private String website;
  private AirlineStatus status;
  private String alliance;

  private Long ownerId;
  private UserDto owner;
  private Long updatedById;
  private CityResponse headquartersCity;
  private Support support;

  private Instant updatedAt;
  private Instant createdAt;
}
