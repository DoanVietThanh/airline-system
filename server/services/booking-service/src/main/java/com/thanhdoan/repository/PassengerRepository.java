package com.thanhdoan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanhdoan.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
