package com.thanhdoan.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thanhdoan.enums.BookingStatus;
import com.thanhdoan.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

  List<Booking> findByUserId(Long userId);

  Long countByFlightInstanceId(Long flightInstanceId);

  @Query("""
      select distinct b from Booking b
      left join fetch b.passengers p
      where b.airlineId = :airlineId
      and (:search is null or lower(b.bookingReference) like lower(concat('%', :search, '%'))
        or lower(p.firstName) like lower(concat('%', :search, '%'))
        or lower(p.lastName) like lower(concat('%', :search, '%'))
        or lower(p.email) like lower(concat('%', :search, '%'))
        or lower(p.contactInfo.email) like lower(concat('%', :search, '%'))
        or lower(p.contactInfo.phone) like lower(concat('%', :search, '%'))
        )
      and (:status is null or b.status = :status)
      and (:flightInstanceId is null or b.flightInstanceId = :flightInstanceId)
      """)
  List<Booking> findByAirlineWithFilter(
      @Param("airlineId") Long airlineId,
      @Param("search") String search,
      @Param("status") BookingStatus status,
      @Param("flightInstanceId") Long flightInstanceId,
      @Param("sort") Sort sort);

  boolean existsByBookingReference(String bookingReference);

}
