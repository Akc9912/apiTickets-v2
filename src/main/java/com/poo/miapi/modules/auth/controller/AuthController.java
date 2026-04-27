package com.poo.miapi.modules.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poo.miapi.modules.auth.api.dto.LoginRequest;
import com.poo.miapi.modules.auth.api.dto.LoginResponse;
import com.poo.miapi.modules.auth.service.AuthServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthServiceImpl authServiceImpl;

    public ResponseEntity<?> login(LoginRequest request) {
        LoginResponse response = authServiceImpl.login(request);
        return ResponseEntity.ok(response);
    }
}
