package com.thanhdoan.service;

import java.util.List;

import com.thanhdoan.enums.BookingStatus;
import com.thanhdoan.payload.request.BookingRequest;
import com.thanhdoan.payload.response.BookingResponse;

public interface BookingService {

  BookingResponse createBooking(BookingRequest bookingRequest, Long userId);

  BookingResponse updateBooking(Long bookingId, BookingRequest bookingRequest);

  BookingResponse getBookingById(Long bookingId) throws Exception;

  List<BookingResponse> getAllBookingsByAirline(
      Long airlineId, String searchQuery, BookingStatus status,
      Long flightInstanceId, String sortDirection);

  List<BookingResponse> getBookingsByUser(Long userId);

  BookingResponse cancelBooking(Long bookingId) throws Exception;

  void deleteBooking(Long bookingId) throws Exception;
}
