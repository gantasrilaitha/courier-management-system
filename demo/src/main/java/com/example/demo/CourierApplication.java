package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Hibernate SessionFactory is managed automatically by Spring Boot when you use spring-boot-starter-data-jpa.
//Session: trnsaction in a database
//EntityManager is the interface , high level abstraction,part of JPA
//┌───────────────────────────┐
// │      Spring Boot App       │
// │  (Your code: Repositories, │
// │   Entities, Controllers)   │
// └─────────────▲──────────────┘
//               │
//               │
//               │
//    Spring Data JPA (JpaRepository)
//               │
//               ▼
// ┌───────────────────────────┐
// │   EntityManagerFactory     │
// │  (Manages EntityManager)   │
// │       (JPA level)          │
// └─────────────▲──────────────┘
//               │
//               │
//               ▼
// ┌───────────────────────────┐
// │    Hibernate SessionFactory│
// │    (Manages Sessions)      │
// │        (Hibernate level)   │
// └─────────────▲──────────────┘
//               │
//               │
//               ▼
//      ┌─────────────────────┐
//      │     Database         │
//      │   (MySQL, etc.)      │
//      └─────────────────────┘
// Explanation:
// Spring Boot Application:

// This includes your application code such as repositories (e.g., JpaRepository), entities (JPA annotated classes), and controllers. You typically work with JPA repositories to interact with the database.
// Spring Data JPA:

// Spring Data JPA provides an abstraction over JPA to handle database operations. It integrates with the EntityManager to interact with the database.
// EntityManagerFactory:

// Spring Boot automatically configures the EntityManagerFactory, which is responsible for creating EntityManager instances. The EntityManager is used to persist and retrieve entities in JPA. Internally, it delegates these operations to Hibernate.
// Hibernate SessionFactory:

// The SessionFactory is part of Hibernate, which manages Session instances. The Session represents a connection to the database and handles tasks like querying and saving data. The EntityManagerFactory creates and manages the SessionFactory.
// Database:

// Finally, the SessionFactory (via sessions) communicates with the database (such as MySQL) to perform the actual persistence operations.
// In summary, you primarily interact with the JPA repository or EntityManager in your Spring Boot application, while Hibernate’s SessionFactory works behind the scenes to manage sessions and database transactions.

@SpringBootApplication
public class CourierApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourierApplication.class, args);
	}

}
