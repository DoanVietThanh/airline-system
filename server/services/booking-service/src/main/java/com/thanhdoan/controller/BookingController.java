package com.thanhdoan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.enums.BookingStatus;
import com.thanhdoan.payload.request.BookingRequest;
import com.thanhdoan.payload.response.BookingResponse;
import com.thanhdoan.service.BookingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

  private final BookingService bookingService;

  @PostMapping
  public ResponseEntity<BookingResponse> createBooking(@RequestHeader("X-User-Id") Long userId,
      @Valid @RequestBody BookingRequest bookingRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(bookingRequest, userId));
  }

  @GetMapping("/{bookingId}")
  public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long bookingId) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingById(bookingId));
  }

  @GetMapping("/airline")
  public ResponseEntity<List<BookingResponse>> getBookingsByAirline(@RequestHeader("X-User-Id") Long userId,
      @RequestParam(required = false) String searchQuery,
      @RequestParam(required = false) BookingStatus status,
      @RequestParam(required = false) Long flightInstanceId,
      @RequestParam(defaultValue = "DESC") String sortDirection) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(bookingService.getAllBookingsByAirline(userId, searchQuery, status, flightInstanceId, sortDirection));
  }

  @GetMapping("/user/history")
  public ResponseEntity<List<BookingResponse>> getBookingsByUser(@RequestHeader("X-User-Id") Long userId) {
    return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingsByUser(userId));
  }

  @PostMapping("/{bookingId}/cancel")
  public ResponseEntity<BookingResponse> cancelBooking(@PathVariable Long bookingId) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(bookingService.cancelBooking(bookingId));
  }

  @DeleteMapping("/{bookingId}")
  public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) throws Exception {
    bookingService.deleteBooking(bookingId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PatchMapping("/{bookingId}")
  public ResponseEntity<BookingResponse> updateBooking(@PathVariable Long bookingId,
      @RequestBody BookingRequest bookingRequest) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(bookingService.updateBooking(bookingId, bookingRequest));
  }

}
