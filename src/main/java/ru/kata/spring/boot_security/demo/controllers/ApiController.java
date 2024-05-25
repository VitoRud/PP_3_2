package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final UserService us;

    @Autowired
    public ApiController(UserService us) {
        this.us = us;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(Principal principal) {
        User user = us.findByUsername(principal.getName());
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> user(@PathVariable long id) {
        try {
            User user = us.getOneUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> userList() {
        try {
            List<User> list = us.userList();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        us.addUser(user);
    }

    @PatchMapping("/users")
    public void updateUser(@RequestBody User user) {
        us.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public String deleterUser(@PathVariable long id) {
        us.removeUser(id);
        return "User with ID = " + id + " was delete";
    }

}