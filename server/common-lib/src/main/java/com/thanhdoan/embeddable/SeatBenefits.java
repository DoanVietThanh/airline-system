package com.thanhdoan.embeddable;

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
public class SeatBenefits {

  @Builder.Default
  private Boolean extraSeatSpace = false;

  @Builder.Default
  private Boolean preferredSeatChoice = false;

  @Builder.Default
  private Boolean advanceSeatSelection = false;

  @Builder.Default
  private Boolean guaranteedSeatTogether = false;

}
