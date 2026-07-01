package com.thanhdoan.payload.request;

import java.util.List;

import com.thanhdoan.enums.CabinClassType;
import com.thanhdoan.embeddable.ContactInfo;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

  @NotNull(message = "Flight ID is required")
  private Long flightId;

  @NotNull(message = "Flight Instance ID is required")
  private Long flightInstanceId;

  @NotNull(message = "Cabin Class is required")
  private CabinClassType cabinClass;

  @NotNull(message = "Fare ID is required")
  private Long fareId;

  @NotNull(message = "Passengers are required")
  @Size(min = 1, message = "At least one passenger is required")
  private List<PassengerRequest> passengers;

  // contactInfo, ancillaryInds, mealIds, seatNumbers
  private ContactInfo contactInfo;

  private List<Long> ancillaryIds;

  private List<Long> mealIds;

  private List<String> seatNumbers;

}
