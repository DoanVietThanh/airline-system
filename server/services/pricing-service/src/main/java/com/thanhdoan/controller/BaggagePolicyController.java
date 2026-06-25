package com.thanhdoan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.request.BaggagePolicyRequest;
import com.thanhdoan.payload.response.BaggagePolicyResponse;
import com.thanhdoan.service.BaggagePolicyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/baggage-policies")
@RequiredArgsConstructor
public class BaggagePolicyController {

  private final BaggagePolicyService baggagePolicyService;

  @PostMapping
  public ResponseEntity<BaggagePolicyResponse> createBaggagePolicy(
      @Valid @RequestBody BaggagePolicyRequest baggagePolicyRequest) throws Exception {
    BaggagePolicyResponse baggagePolicyResponse = baggagePolicyService.createBaggagePolicy(baggagePolicyRequest);
    return ResponseEntity.ok(baggagePolicyResponse);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaggagePolicyResponse> getBaggagePolicyById(@PathVariable Long id) throws Exception {
    BaggagePolicyResponse baggagePolicyResponse = baggagePolicyService.getBaggagePolicyById(id);
    return ResponseEntity.ok(baggagePolicyResponse);
  }

  @GetMapping("/fare/{fareId}")
  public ResponseEntity<BaggagePolicyResponse> getBaggagePolicyByFareId(@PathVariable Long fareId) throws Exception {
    BaggagePolicyResponse baggagePolicyResponse = baggagePolicyService.getBaggagePolicyByFareId(fareId);
    return ResponseEntity.ok(baggagePolicyResponse);
  }

  @GetMapping("/airline/{airlineId}")
  public ResponseEntity<List<BaggagePolicyResponse>> getBaggagePoliciesByAirlineId(@PathVariable Long airlineId)
      throws Exception {
    List<BaggagePolicyResponse> baggagePolicyResponses = baggagePolicyService.getBaggagePoliciesByAirlineId(airlineId);
    return ResponseEntity.ok(baggagePolicyResponses);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaggagePolicyResponse> updateBaggagePolicy(@PathVariable Long id,
      @RequestBody BaggagePolicyRequest baggagePolicyRequest) throws Exception {
    BaggagePolicyResponse baggagePolicyResponse = baggagePolicyService.updateBaggagePolicy(id, baggagePolicyRequest);
    return ResponseEntity.ok(baggagePolicyResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBaggagePolicy(@PathVariable Long id) throws Exception {
    baggagePolicyService.deleteBaggagePolicy(id);
    return ResponseEntity.noContent().build();
  }

}
