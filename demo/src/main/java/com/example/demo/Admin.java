package com.example.demo;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "admin")
@Entity
/*
 * public class Admin {
 * 
 * @Id
 * private String username;
 * private String password;
 * 
 * // Getters and setters
 * public String getUsername() {
 * return username;
 * }
 * 
 * public void setUsername(String username) {
 * this.username = username;
 * }
 * 
 * public String getPassword() {
 * return password;
 * }
 * 
 * public void setPassword(String password) {
 * this.password = password;
 * }
 * }
 */

public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    private String password;

}