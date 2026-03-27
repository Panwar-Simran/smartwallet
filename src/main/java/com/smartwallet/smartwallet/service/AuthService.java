package com.smartwallet.smartwallet.service;

import com.smartwallet.smartwallet.dto.RegisterRequest;
import com.smartwallet.smartwallet.model.User;
import com.smartwallet.smartwallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
   private UserRepository userRepository;

    @Autowired
     private PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {

        //  check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "User already exists";
        }

        // create new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // encrypt password using BCrypt
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole("USER");

        // manual timestamps (as you decided)
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // save user
        userRepository.save(user);

        return "User registered successfully";
    }
}
