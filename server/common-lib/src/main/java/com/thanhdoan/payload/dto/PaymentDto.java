package com.thanhdoan.payload.dto;

import com.thanhdoan.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

  private Long id;
  private PaymentStatus status;
}
