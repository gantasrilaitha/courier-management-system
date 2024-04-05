package com.example.demo;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "newstaff")
@Entity

public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private String username;
    private Long id;
    private String name;
    private String location;
    private String password;

}