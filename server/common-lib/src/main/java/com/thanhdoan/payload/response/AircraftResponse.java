package com.thanhdoan.payload.response;

import java.time.Instant;
import java.time.LocalDate;

import com.thanhdoan.enums.AircraftStatus;

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
public class AircraftResponse {
  private Long id;
  private String code;
  private String model;
  private String manufacturer;
  private Integer seatingCapacity;
  private Integer economySeats;
  private Integer premiumEconomySeats;
  private Integer businessSeats;
  private Integer firstClassSeats;
  private Integer rangeKm;
  private Integer cruisingSpeedKmh;
  private Integer maxAltitudeFt;
  private Integer yearOfManufacture;
  private LocalDate registrationDate;
  private LocalDate nextMaintenanceDate;

  private AircraftStatus status;
  private Boolean isAvailable;

  private Long airlineId;
  private String airlineName;
  private String airlineIataCode;

  private Long currentAirportId;
  private String currentAirportCity;
  private String currentAirportName;
  private String currentAirportCode;

  private Integer totalSeats;
  private Boolean requiresMaintenance;
  private Boolean isOperational;

  private Instant updatedAt;
  private Instant createdAt;
}
