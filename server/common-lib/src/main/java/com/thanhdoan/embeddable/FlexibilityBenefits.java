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
public class FlexibilityBenefits {

  // Boolean: freeDateChange, partialRefund, fullRefund
  @Builder.Default
  @Column(name = "free_date_change")
  private Boolean freeDateChange = false;

  @Builder.Default
  @Column(name = "partial_refund", nullable = false)
  private Boolean partialRefund = false;

  @Builder.Default
  @Column(name = "full_refund", nullable = false)
  private Boolean fullRefund = false;

}
