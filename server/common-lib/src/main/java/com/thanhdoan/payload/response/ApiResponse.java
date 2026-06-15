package com.thanhdoan.payload.response;

import lombok.Data;

@Data
public class ApiResponse {
  private boolean success;
  private String message;
  private Object data;

}
