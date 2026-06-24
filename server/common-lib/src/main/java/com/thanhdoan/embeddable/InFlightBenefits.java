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
public class InFlightBenefits {

  @Builder.Default
  @Column(name = "complimentary_meals", nullable = false)
  private Boolean complimentaryMeals = false;

  @Builder.Default
  @Column(name = "premium_meal_choice", nullable = false)
  private Boolean premiumMealChoice = false;

  @Builder.Default
  @Column(name = "in_flight_internet", nullable = false)
  private Boolean inFlightInternet = false;

  @Builder.Default
  @Column(name = "in_flight_entertainment", nullable = false)
  private Boolean inFlightEntertainment = false;

  @Builder.Default
  @Column(name = "complimentary_beverages", nullable = false)
  private Boolean complimentaryBeverages = false;

}
