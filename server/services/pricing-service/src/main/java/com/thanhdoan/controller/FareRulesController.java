package com.thanhdoan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhdoan.payload.request.FareRulesRequest;
import com.thanhdoan.payload.response.FareRulesResponse;
import com.thanhdoan.service.FareRulesService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fare-rules")
public class FareRulesController {

  private final FareRulesService fareRulesService;

  @PostMapping
  public ResponseEntity<FareRulesResponse> createFareRules(@Valid @RequestBody FareRulesRequest fareRulesRequest)
      throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED).body(fareRulesService.createFareRules(fareRulesRequest));
  }

  @GetMapping("/{id}")
  public ResponseEntity<FareRulesResponse> getFareRulesById(@PathVariable Long id) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(fareRulesService.getFareRulesById(id));
  }

  @GetMapping("/fare/{fareId}")
  public ResponseEntity<List<FareRulesResponse>> getFareRulesByFareId(@PathVariable Long fareId) throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(fareRulesService.getFareRulesByFareId(fareId));
  }

  @GetMapping("/airline/{airlineId}")
  public ResponseEntity<List<FareRulesResponse>> getFareRulesByAirlineId(@PathVariable Long airlineId)
      throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(fareRulesService.getFareRulesByAirlineId(airlineId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<FareRulesResponse> updateFareRules(@PathVariable Long id,
      @Valid @RequestBody FareRulesRequest fareRulesRequest)
      throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(fareRulesService.updateFareRules(id, fareRulesRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFareRules(@PathVariable Long id) throws Exception {
    fareRulesService.deleteFareRules(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
