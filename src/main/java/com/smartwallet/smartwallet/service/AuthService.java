package com.smartwallet.smartwallet.service;

import com.smartwallet.smartwallet.dto.LoginRequest;
import com.smartwallet.smartwallet.dto.RegisterRequest;
import com.smartwallet.smartwallet.exception.UserAlreadyExistsException;
import com.smartwallet.smartwallet.model.User;
import com.smartwallet.smartwallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // REGISTER
    public String register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new UserAlreadyExistsException("User already exists");

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone_no(request.getPhone_no());
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        return "User registered successfully";
    }

    // LOGIN
    public String login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid email or password");
        }

        return jwtService.generateToken(request.getEmail());
    }
}