package ru.kata.spring.boot_security.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @GetMapping(value = "/")
    public String printWelcome() {
        return "index";
    }

    @GetMapping(value = "/user")
    public String userPage() {
        return "user";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
