package com.thanhdoan.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.thanhdoan.embeddable.ContactInfo;
import com.thanhdoan.enums.BookingStatus;
import com.thanhdoan.enums.CabinClassType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  // bookingReference, userId, flightId
  @Column(nullable = false, unique = true)
  private String bookingReference;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private Long flightId;

  @Column(nullable = false)
  private Long flightInstanceId;

  @Column(nullable = false)
  private Long airlineId;

  @Enumerated(EnumType.STRING)
  @Builder.Default
  private CabinClassType cabinClass = CabinClassType.ECONOMY;

  @Column(nullable = false)
  private Long fareId;

  private Boolean flexibleTicket;

  private LocalDateTime ticketTimeLimit;

  @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private Set<Passenger> passengers = new HashSet<>();

  @ElementCollection
  private List<Long> seatInstanceIds;

  @ElementCollection
  private List<Long> ancillaryIds;

  @ElementCollection
  private List<Long> mealIds;

  @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private Set<Ticket> tickets = new HashSet<>();

  private Long paymentId;

  @Enumerated(EnumType.STRING)
  private BookingStatus status;

  @CreatedDate
  private Instant bookingDate;

  @UpdateTimestamp
  private Instant lastModifiedDate;

  private boolean ticketIssued;

  private ContactInfo contactInfo;
}
