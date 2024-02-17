package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourierController {

    @GetMapping("/home")
    public String home(Model model) {
        System.out.println("Homepg");
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @Autowired
    private AdminService adminService;

    /*
     * @GetMapping("/admin")
     * public String adminPage(User user, Model model) {
     * model.addAttribute("user", new User());
     * return "admin";
     * }
     */

    @GetMapping("/admin")
    public String adminPage(User user, Model model) {
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("/login")
    public String processLogin(User user, Model model) {
        boolean isValid = adminService.validateLogin(user.getPassword(), user.getUsername());
        if (isValid) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "admin";
        }
    }
}
