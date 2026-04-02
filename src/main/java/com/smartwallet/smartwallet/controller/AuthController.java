package com.smartwallet.smartwallet.controller;

import com.smartwallet.smartwallet.dto.AuthResponse;
import com.smartwallet.smartwallet.dto.LoginRequest;
import com.smartwallet.smartwallet.dto.RegisterRequest;
import com.smartwallet.smartwallet.dto.RegisterResponse;
import com.smartwallet.smartwallet.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        String msg = authService.register(request);
        RegisterResponse response = new RegisterResponse(msg);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        String token = authService.login(request);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}