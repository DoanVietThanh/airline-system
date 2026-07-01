package com.thanhdoan.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.thanhdoan.enums.BookingStatus;
import com.thanhdoan.model.Booking;
import com.thanhdoan.model.Passenger;
import com.thanhdoan.payload.dto.PaymentDto;
import com.thanhdoan.payload.request.BookingRequest;
import com.thanhdoan.payload.response.BookingResponse;
import com.thanhdoan.payload.response.FareResponse;
import com.thanhdoan.payload.response.FlightCabinAncillaryResponse;
import com.thanhdoan.payload.response.FlightInstanceResponse;
import com.thanhdoan.payload.response.FlightMealResponse;
import com.thanhdoan.payload.response.FlightResponse;
import com.thanhdoan.payload.response.PassengerResponse;
import com.thanhdoan.payload.response.SeatInstanceResponse;
import com.thanhdoan.payload.response.TicketResponse;

public class BookingMapper {

	public static Booking toEntity(BookingRequest bookingRequest, Long userId,
			Set<Passenger> passengers, String bookingReference) {
		return Booking.builder()
				.bookingReference(bookingReference)
				.userId(userId)
				.flightId(bookingRequest.getFlightId())
				.flightInstanceId(bookingRequest.getFlightInstanceId())
				.fareId(bookingRequest.getFareId())
				.contactInfo(bookingRequest.getContactInfo())
				.passengers(passengers)
				.cabinClass(bookingRequest.getCabinClass())
				.ancillaryIds(bookingRequest.getAncillaryIds())
				.mealIds(bookingRequest.getMealIds())
				.status(BookingStatus.PENDING)
				.build();
	}

	public static BookingResponse toResponse(Booking booking, PaymentDto paymentDto,
			FareResponse fareResponse, FlightResponse flightResponse,
			FlightInstanceResponse flightInstanceResponse, List<FlightCabinAncillaryResponse> ancillaries,
			List<FlightMealResponse> meals, List<SeatInstanceResponse> seats) {

		List<PassengerResponse> passengerResponses = booking.getPassengers() != null ? booking.getPassengers().stream()
				.map(PassengerMapper::toResponse)
				.collect(Collectors.toList()) : null;

		List<TicketResponse> ticketResponses = booking.getTickets() != null ? booking.getTickets().stream()
				.map(TicketMapper::toResponse)
				.collect(Collectors.toList()) : null;

		return BookingResponse.builder()
				.id(booking.getId())
				.bookingReference(booking.getBookingReference())
				.userId(booking.getUserId())
				.flightId(booking.getFlightId())
				.flightNumber(flightResponse != null ? flightResponse.getFlightNumber() : null)
				.flightName(flightResponse != null && flightResponse.getArrivalAirport() != null
						&& flightResponse.getDepartureAirport() != null
								? flightResponse.getArrivalAirport().getCity().getName() + " - "
										+ flightResponse.getDepartureAirport().getCity().getName()
								: null)

				.departureTime(flightInstanceResponse != null ? flightInstanceResponse.getDepartureDateTime() : null)
				.arrivalTime(flightInstanceResponse != null ? flightInstanceResponse.getArrivalDateTime() : null)
				.flightDuration(flightInstanceResponse != null ? flightInstanceResponse.getFormattedDuration() : null)

				// airport details
				.departureAirport(
						flightResponse != null && flightResponse != null
								? flightResponse.getDepartureAirport().getCity().getName()
								: null)
				.arrivalAirport(
						flightResponse != null && flightResponse != null
								? flightResponse.getArrivalAirport().getCity().getName()
								: null)
				.status(booking.getStatus())
				.bookingDate(booking.getBookingDate())
				.lastModifiedDate(booking.getLastModifiedDate())
				.passengers(passengerResponses)
				.tickets(ticketResponses)

				.totalPassengers(booking.getPassengers() != null ? booking.getPassengers().size() : 0)
				.ancillaries(ancillaries)
				.meals(meals)
				.seatInstances(seats)
				.paymentStatus(paymentDto != null ? paymentDto.getStatus() : null)

				// fare details
				.fareName(fareResponse != null ? fareResponse.getName() : null)
				.fareBaseFare(fareResponse != null ? fareResponse.getBaseFare() : null)
				.fareTaxesAndFees(fareResponse != null ? fareResponse.getTaxesAndFees() : null)
				.fareAirlineFees(fareResponse != null ? fareResponse.getAirlineFees() : null)
				.totalAmount(fareResponse != null ? fareResponse.getTotalPrice() : null)

				// contact info
				.contactInfo(booking.getContactInfo())
				.build();
	}
}
