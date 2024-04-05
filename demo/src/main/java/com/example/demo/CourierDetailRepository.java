package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface CourierDetailRepository extends JpaRepository<CourierDetails, String> {
    Optional<CourierDetails> findByTrackingNumber(String trackingNumber);

    List<CourierDetails> findByFromName(String fromName);
}
