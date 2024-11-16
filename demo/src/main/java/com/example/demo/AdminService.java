package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//Instead of an XML file, Spring Boot applications typically use Java classes annotated with @Configuration to define beans.
//Spring Boot automatically scans for components (beans) by default using annotations like @Component, @Service, @Repository, and @Controller.

//servie(model) class & interacts with db
@Service
public class AdminService {

    @Autowired // added depencies using @Autowired
    private AdminRepository adminRepository;

    public boolean validateLogin(String password, String username) {
        username = username.trim();
        password = password.trim();
        Admin admin = adminRepository.findByPassword(password);

        System.out.println(username);
        System.out.println(password);
        return admin != null;
    }
}
