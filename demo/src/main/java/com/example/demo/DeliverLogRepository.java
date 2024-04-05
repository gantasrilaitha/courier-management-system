package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverLogRepository extends JpaRepository<DeliverLog, String> {
    Optional<CourierDetails> findByTrackingNumber(String trackingNumber);
}
