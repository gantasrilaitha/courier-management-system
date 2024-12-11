# **Courier Management System- Java Springboot Application** 🚚
A Java SpringBoot web application,for *Courier Services Companies*, following the MVC Architecture framework, design principles and patterns.

Enables Admins to update courier costs and manage staff actions, Staff to view pickup and delivery logs based on their working location, and Customers to place couriers, view the courier status at any time, and see their booking history too!

## **Some Explicit Actors & Functionalities!**
- 👥**Admins**: Update courier costs, manage staff actions.
- 👥**Staff**: View pickup and delivery logs based on their working location.
- 👥**Customers**: Place courier requests, view courier status, and access booking history.

## **Design Principles & Patterns Used**
- **Patterns :** MVC, Singleton, DAO(Data Access Object), Adapter
- **Principles :** Single Responsibility Principle(SRP), Separation of Concerns, Open Closed Principle(OCP), Liskov Substitution Principle(LSP)
  
## **Tech-Stack used!**
<p align="center">
  <img src="https://img.icons8.com/color/48/000000/java-coffee-cup-logo.png" alt="Java" width="40" height="40"/>
  <img src="https://img.icons8.com/color/48/000000/spring-logo.png" alt="SpringBoot" width="40" height="40"/>
  <img src="https://img.icons8.com/color/48/000000/mysql-logo.png" alt="MySQL" width="40" height="40"/>
  <img src="https://img.icons8.com/color/48/000000/html-5.png" alt="HTML" width="40" height="40"/>
  <img src="https://img.icons8.com/color/48/000000/css3.png" alt="CSS" width="40" height="40"/>
  <img src="https://img.icons8.com/color/48/000000/javascript.png" alt="JavaScript" width="40" height="40"/>
  <img src="https://img.icons8.com/nolan/48/api.png" alt="REST API" width="40" height="40"/>
</p>

## **Prerequisites**
Make sure you have the following installed on your system:
<p align="center">
  <a href="https://www.oracle.com/java/technologies/javase-jdk17-downloads.html">Java JDK (v17 or higher)</a> &nbsp;&nbsp;&nbsp;
  <a href="https://maven.apache.org/download.cgi">Apache Maven</a> &nbsp;&nbsp;&nbsp;
  <a href="https://www.mysql.com/downloads/">MySQL</a>
</p>

## **How to use❓**
Refer to [Codebase](https://github.com/gantasrilaitha/courier-management-java/tree/main/demo/src/main/java/com/example/demo)
1. change directory :```cd demo```
2. ```mvn spring-boot:run``` to run the application
3. visit ```localhost:8080/home``` to see the application running
4. you can test the endpoints using Postman

## **Note**
1. Create a database in mysql(<DATABASE_NAME>) under which all entity tables will get created
2. Create ``.env `` file is created containing all database connections credentials. It should contain the following:
  
  - DB_URL=jdbc:mysql://localhost:<MYSQL_DATABASE_PORT>/<DATABASE_NAME>
  - DB_USERNAME=<MYSQL_DATABASE_USERNAME>
  - DB_PASSWORD=<MYSQL_DATABSE_PASSWORD>
  
3. Have some dummy username & password under the admin , newstaff and customer lopgin tables.
4. Ensure `.env` file is created under the root directory i.e ```demo``` .




