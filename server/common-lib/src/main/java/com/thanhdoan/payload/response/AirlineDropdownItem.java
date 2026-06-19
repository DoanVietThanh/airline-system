package com.thanhdoan.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirlineDropdownItem {
  private Long id;
  private String name;
  private String iataCode;
  private String icaoCode;
  private String logoUrl;
  private String country;

}
