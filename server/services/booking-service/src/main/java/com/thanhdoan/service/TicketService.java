package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.model.Booking;
import com.thanhdoan.model.Ticket;

public interface TicketService {

  List<Ticket> generateTicketsForBooking(Booking booking);
}
