package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CourierDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pickupAddress;
    private String pickupCity;
    private String toName;
    private String fromName;
    // @Column(name = "to_mobile_no") // Specify the column name explicitly
    private String toMobile;
    // private String toMobileNo;
    private String destinationAddress;
    private String destinationCity;
    private String paymentMethod;
    private double weightKg;
    private double totalCost;
    private String trackingNumber;
    private String status;

    // Constructors
    public CourierDetails() {
    }

    public CourierDetails(String pickupAddress, String pickupCity, String toName, String toMobile,
            String destinationAddress, String fromName, String paymentMethod,
            String destinationCity, double weightKg, double totalCost, String trackingNumber) {
        this.pickupAddress = pickupAddress;
        this.pickupCity = pickupCity;
        this.toName = toName;
        this.fromName = fromName;
        this.toMobile = toMobile;
        this.destinationAddress = destinationAddress;
        this.destinationCity = destinationCity;
        this.weightKg = weightKg;
        this.totalCost = totalCost;
        this.trackingNumber = trackingNumber;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    // Getters and setters
    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getPickupCity() {
        return pickupCity;
    }

    public void setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToMobile() {
        return toMobile;
    }

    public void setToMobile(String toMobileNo) {
        this.toMobile = toMobileNo;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getWeight() {
        return weightKg;
    }

    public void setWeight(double weight) {
        this.weightKg = weight;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
