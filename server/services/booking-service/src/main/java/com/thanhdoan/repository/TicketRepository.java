package com.thanhdoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thanhdoan.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

  @Query("""
      SELECT t FROM Ticket t
      left join fetch t.booking
      left join fetch t.passenger
      where t.booking.id = :bookingId
      """)
  List<Ticket> findByBookingIdWithDetails(@Param("bookingId") Long bookingId);

  List<Ticket> findByBookingId(Long bookingId);

  boolean existsByTicketNumber(String ticketNumber);
}
