package com.thanhdoan.model;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.thanhdoan.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Passenger {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  private String email;

  private String phone;

  @Column(nullable = false)
  private LocalDate dateOfBirth;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  private String nationality;

  private Long primaryUserId;

  @ManyToOne
  private Booking booking;

  @Builder.Default
  private Boolean isActive = true;

  @Version
  private Long version;

  @CreatedDate
  private Instant createdAt;

  @LastModifiedDate
  private Instant updatedAt;

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public int getAge() {
    return LocalDate.now().getYear() - dateOfBirth.getYear();
  }

  public boolean isAdult() {
    return getAge() >= 18;
  }
}
