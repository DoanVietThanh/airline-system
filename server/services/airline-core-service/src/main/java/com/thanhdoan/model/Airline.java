package com.thanhdoan.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.thanhdoan.embeddable.Support;
import com.thanhdoan.enums.AirlineStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "airlines")
public class Airline {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private Long id;

  @Size(min = 2, max = 2, message = "IATA code must be exactly 2 characters")
  @Column(name = "iata_code", length = 2, nullable = false, unique = true)
  private String iataCode;

  @Size(min = 3, max = 3, message = "ICAO code must be exactly 3 characters")
  @Column(name = "icao_code", length = 3, nullable = false, unique = true)
  private String icaoCode;

  @Column(nullable = false)
  private String name;

  private String alias;

  @Column(nullable = false)
  private String country;

  private String logoUrl;

  private String website;

  @Enumerated(EnumType.STRING)
  @Builder.Default
  @Column(nullable = false, length = 20)
  private AirlineStatus status = AirlineStatus.ACTIVE;

  private String alliance;

  @Embedded
  private Support support;

  // Cross-service reference: stored as ID (City lives in another service)
  @Column(name = "headquarters_city_id")
  private Long headquartersCityId;

  // Cross-service reference: stored as ID (User lives in user-service)
  @Column(name = "owner_id", updatable = false, nullable = false)
  private Long ownerId;

  @Column(name = "updated_by_user_id")
  private Long updatedById;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  private Instant createdAt;

  @LastModifiedDate
  @Column(nullable = false)
  private Instant updatedAt;
}
