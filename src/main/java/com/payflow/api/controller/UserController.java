package com.payflow.api.controller;

import com.payflow.api.entity.User;
import com.payflow.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.println("createUser (with @RequestBody) received: "
                + user.getName() + ", " + user.getUpiId() + ", " + user.getBalance());
        return userService.registerUser(user);
    }

    // Demonstrates the difference @RequestBody makes: without it, Spring does
    // not deserialize the HTTP body into this User - it just binds an empty,
    // default-constructed object, so every field below prints null/0.
    @PostMapping("/no-request-body-demo")
    public User createUserWithoutRequestBody(User user) {
        System.out.println("createUser (without @RequestBody) received: "
                + user.getName() + ", " + user.getUpiId() + ", " + user.getBalance());
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/upi/{upiId}")
    public ResponseEntity<User> getUserByUpiId(@PathVariable String upiId) {
        return userService.findByUpiId(upiId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/balance-above/{amount}")
    public List<User> getUsersWithBalanceAbove(@PathVariable Double amount) {
        return userService.findUsersWithBalanceAbove(amount);
    }
}
