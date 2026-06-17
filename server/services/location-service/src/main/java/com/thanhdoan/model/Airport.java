package com.thanhdoan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thanhdoan.embeddable.Address;
import com.thanhdoan.embeddable.GeoCode;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true, nullable = false, length = 3)
  private String iataCode;

  @Column(nullable = false)
  private String name;

  @Embedded
  private Address address;

  @Embedded
  private GeoCode geoCode;

  @Column(name = "time_zone_id", length = 50)
  private String timeZoneId;

  @ManyToOne(optional = false)
  @JoinColumn(name = "city_id", nullable = false)
  @JsonIgnore
  private City city;

  @Transient
  @JsonIgnore
  public String getDetailedName() {
    if (city != null && city.getCountryCode() != null) {
      return name.toUpperCase() + "/" + city.getCountryCode();
    }
    return name.toUpperCase();
  }
}
