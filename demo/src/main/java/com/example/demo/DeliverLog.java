package com.example.demo;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//entity classes annotated with JPA annotations(@Entity) that will map to your database tables.
@Entity
public class DeliverLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increments primary key values(ex:1,2,3...), doesnt
                                                        // create sequence table unlike GenerationType.AUTO
    private Long id;
    private String trackingNumber;
    private String toName;
    private String destinationAddress;
    private String destinationCity;

    // Constructors
    public DeliverLog() {
    }

    public DeliverLog(String trackingNumber, String toName, String destinationAddress, String destinationCity) {
        this.trackingNumber = trackingNumber;
        this.toName = toName;
        this.destinationAddress = destinationAddress;
        this.destinationCity = destinationCity;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
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

    // toString method (optional)
    @Override
    public String toString() {
        return "DeliverLog{" +
                "id=" + id +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", toName='" + toName + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                '}';
    }
}
