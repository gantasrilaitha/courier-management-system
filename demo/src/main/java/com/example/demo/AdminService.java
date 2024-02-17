package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
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
