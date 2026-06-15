package com.thanhdoan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.response.ApiResponse;

@RestController
public class HomeController {
  @GetMapping("/")
  public ApiResponse home() {
    ApiResponse response = new ApiResponse();
    response.setSuccess(true);
    response.setMessage("Hello World");
    response.setData("Hello World");
    return response;
  }
}