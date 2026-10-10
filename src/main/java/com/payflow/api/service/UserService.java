package com.payflow.api.service;

import com.payflow.api.entity.User;
import com.payflow.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // At startup, Spring's component scan finds this class (@Service) and the
    // UserRepository interface (a Spring Data JPA repository). It generates a
    // proxy implementation of UserRepository and registers both as beans in the
    // application context. @Autowired then asks the container for a bean that
    // satisfies the UserRepository type, and the container injects that proxy
    // here via constructor injection - no manual "new UserRepository()" needed.
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUpiId(String upiId) {
        return userRepository.findByUpiId(upiId);
    }

    public List<User> findUsersWithBalanceAbove(Double amount) {
        return userRepository.findUsersWithBalanceAbove(amount);
    }
}
