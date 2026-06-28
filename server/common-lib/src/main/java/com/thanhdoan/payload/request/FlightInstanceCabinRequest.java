package com.thanhdoan.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightInstanceCabinRequest {

  @NotNull(message = "Flight id is required")
  private Long flightId;

  @NotNull(message = "Flight instance id is required")
  private Long flightInstanceId;

  @NotNull(message = "Cabin class id is required")
  private Long cabinClassId;

  @NotNull(message = "Base fare is required")
  @Positive(message = "Base fare must be greater than 0")
  private Double baseFare;

  @NotNull(message = "Taxes and fee is required")
  @PositiveOrZero(message = "Taxes and fee must be greater than 0")
  private Double taxesAndFee;

  @NotNull(message = "Airline fees is required")
  @PositiveOrZero(message = "Airline fees must be greater than 0")
  private Double airlineFees;

  private Double currentPrice;

  private Boolean isActive;
}
