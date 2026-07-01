package com.thanhdoan.service.impl;

import com.thanhdoan.enums.TicketStatus;
import com.thanhdoan.model.Booking;
import com.thanhdoan.model.Passenger;
import com.thanhdoan.model.Ticket;
import com.thanhdoan.repository.TicketRepository;
import com.thanhdoan.service.TicketService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
  private final TicketRepository ticketRepository;

  @Override
  public List<Ticket> generateTicketsForBooking(Booking booking) {
    List<Ticket> tickets = new ArrayList<>();

    for (Passenger passenger : booking.getPassengers()) {
      String ticketNumber = generateUniqueTicketNumber();
      Ticket ticket = Ticket.builder()
          .ticketNumber(ticketNumber)
          .status(TicketStatus.BOOKED)
          .issuedAt(LocalDateTime.now())
          .booking(booking)
          .passenger(passenger)
          .build();

      Ticket savedTicket = ticketRepository.save(ticket);
      tickets.add(savedTicket);
    }

    return tickets;
  }

  private String generateUniqueTicketNumber() {
    String ticketNumber;
    do {
      String datePart = LocalDateTime.now().toString().substring(0, 10);
      String randomPart = UUID.randomUUID().toString().substring(0, 8);
      ticketNumber = String.format("TKT-%s-%s", datePart, randomPart);
    } while (ticketRepository.existsByTicketNumber(ticketNumber));
    return ticketNumber;
  }
}
