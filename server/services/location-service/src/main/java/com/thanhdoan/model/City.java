package com.thanhdoan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(name = "city_code", nullable = false)
  private String cityCode;

  @Column(name = "country_name", nullable = false)
  private String countryName;

  @Column(name = "country_code", nullable = false)
  private String countryCode;

  @Size(max = 10)
  private String regionCode;

  @Column(name = "time_zone_id", length = 50)
  private String timeZoneId;
}