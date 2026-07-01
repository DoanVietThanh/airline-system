package com.thanhdoan.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.thanhdoan.enums.BookingStatus;
import com.thanhdoan.mapper.BookingMapper;
import com.thanhdoan.model.Booking;
import com.thanhdoan.model.Passenger;
import com.thanhdoan.payload.dto.PaymentDto;
import com.thanhdoan.payload.request.BookingRequest;
import com.thanhdoan.payload.request.PassengerRequest;
import com.thanhdoan.payload.response.BookingResponse;
import com.thanhdoan.payload.response.FareResponse;
import com.thanhdoan.payload.response.FlightCabinAncillaryResponse;
import com.thanhdoan.payload.response.FlightInstanceResponse;
import com.thanhdoan.payload.response.FlightMealResponse;
import com.thanhdoan.payload.response.FlightResponse;
import com.thanhdoan.payload.response.SeatInstanceResponse;
import com.thanhdoan.repository.BookingRepository;
import com.thanhdoan.service.BookingService;
import com.thanhdoan.service.PassengerService;
import com.thanhdoan.service.TicketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

  private final BookingRepository bookingRepository;

  private final PassengerService passengerService;
  private final TicketService ticketService;

  @Override
  public BookingResponse createBooking(BookingRequest bookingRequest, Long userId) {
    // Step 1: create uniq booking reference
    String bookingReference = generateBookingReference();

    // Step 2: create passengers
    Set<Passenger> passengers = new HashSet<>();
    for (PassengerRequest passengerRequest : bookingRequest.getPassengers()) {
      Passenger passenger = passengerService.createPassenger(passengerRequest, userId);
      passengers.add(passenger);
    }

    // Step 3: flight exist
    // Step 4: create booking with pending status
    Booking booking = BookingMapper.toEntity(bookingRequest, userId, passengers, bookingReference);
    booking.setAirlineId(1L);

    List<Long> seatInstanceIds = bookingRequest.getPassengers().stream()
        .map(PassengerRequest::getSeatInstanceId)
        .collect(Collectors.toList());
    booking.setSeatInstanceIds(seatInstanceIds);
    booking = bookingRepository.save(booking);

    // Step 5: Set booking reference on passengers
    for (Passenger passenger : passengers) {
      passenger.setBooking(booking);
    }

    // Step 6: generate tickets for booking
    ticketService.generateTicketsForBooking(booking);

    // Step 7: caculate price
    // Step 8: init payment using payment service

    return convertToBookingResponse(booking);
  }

  @Override
  public BookingResponse updateBooking(Long bookingId, BookingRequest bookingRequest) {
    return null;
  }

  @Override
  public BookingResponse getBookingById(Long bookingId) throws Exception {
    Booking booking = bookingRepository.findById(bookingId)
        .orElseThrow(() -> new Exception("Booking not found with id: " + bookingId));
    return convertToBookingResponse(booking);
  }

  @Override
  public List<BookingResponse> getAllBookingsByAirline(Long airlineId, String searchQuery, BookingStatus status,
      Long flightInstanceId, String sortDirection) {

    Sort.Direction direction = "asc".equalsIgnoreCase(sortDirection)
        ? Sort.Direction.ASC
        : Sort.Direction.DESC;

    Sort sort = Sort.by(direction, "bookingDate");

    List<Booking> bookings = bookingRepository.findByAirlineWithFilter(airlineId, searchQuery, status, flightInstanceId,
        sort);
    return bookings.stream()
        .map(this::convertToBookingResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<BookingResponse> getBookingsByUser(Long userId) {
    List<Booking> bookings = bookingRepository.findByUserId(userId);
    return bookings.stream()
        .map(this::convertToBookingResponse)
        .collect(Collectors.toList());
  }

  @Override
  public BookingResponse cancelBooking(Long bookingId) throws Exception {
    Booking booking = bookingRepository.findById(bookingId)
        .orElseThrow(() -> new Exception("Booking not found with id: " + bookingId));
    booking.setStatus(BookingStatus.CANCELLED);
    booking = bookingRepository.save(booking);
    return convertToBookingResponse(booking);
  }

  @Override
  public void deleteBooking(Long bookingId) throws Exception {
    Booking booking = bookingRepository.findById(bookingId)
        .orElseThrow(() -> new Exception("Booking not found with id: " + bookingId));
    bookingRepository.delete(booking);
  }

  private String generateBookingReference() {
    String reference;
    do {
      reference = "BK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    } while (bookingRepository.existsByBookingReference(reference));
    return reference;
  }

  private BookingResponse convertToBookingResponse(Booking booking) {
    // TODO: when enable feign client use actual responses
    List<FlightCabinAncillaryResponse> ancillaryResponses = new ArrayList<>();
    List<FlightMealResponse> mealResponses = new ArrayList<>();
    PaymentDto paymentDto = new PaymentDto();
    FareResponse fareResponse = new FareResponse();
    FlightResponse flightResponse = new FlightResponse();

    List<SeatInstanceResponse> seatInstanceResponses = new ArrayList<>();

    FlightInstanceResponse flightInstanceResponse = new FlightInstanceResponse();

    return BookingMapper.toResponse(booking, paymentDto, fareResponse, flightResponse, flightInstanceResponse,
        ancillaryResponses, mealResponses, seatInstanceResponses);
  }
}
