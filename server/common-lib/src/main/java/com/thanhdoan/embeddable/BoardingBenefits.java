package com.thanhdoan.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class BoardingBenefits {

  // boolean: priorityBoarding, priorityCheckin, fastTrackSecurity
  @Builder.Default
  @Column(name = "priority_boarding", nullable = false)
  private Boolean priorityBoarding = false;

  @Builder.Default
  @Column(name = "priority_checkin", nullable = false)
  private Boolean priorityCheckin = false;

  @Builder.Default
  @Column(name = "fast_track_security", nullable = false)
  private Boolean fastTrackSecurity = false;

}
