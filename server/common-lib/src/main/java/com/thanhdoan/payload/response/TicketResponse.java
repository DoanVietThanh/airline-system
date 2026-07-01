package com.thanhdoan.payload.response;

import java.time.LocalDateTime;

import com.thanhdoan.enums.TicketStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {

  private Long id;

  private String ticketNumber;
  private TicketStatus status;
  private LocalDateTime issuedAt;

  // Booking details
  private Long bookingId;
  private String bookingReference;

  // Passenger details
  private Long passengerId;
  private String passengerFirstName;
  private String passengerLastName;
  private String passengerEmail;

  // Payment details
  private Long paymentId;
  private Double paymentAmount;
}
