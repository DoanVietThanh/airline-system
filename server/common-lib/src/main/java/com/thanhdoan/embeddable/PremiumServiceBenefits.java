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
public class PremiumServiceBenefits {

  @Builder.Default
  @Column(name = "lounge_access", nullable = false)
  private Boolean loungeAccess = false;

  @Builder.Default
  @Column(name = "airport_transfer", nullable = false)
  private Boolean airportTransfer = false;

}
