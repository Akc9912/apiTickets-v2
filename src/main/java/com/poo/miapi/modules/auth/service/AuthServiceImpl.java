package com.poo.miapi.modules.auth.service;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poo.miapi.modules.auth.api.AuthApi;
import com.poo.miapi.modules.auth.api.dto.*;
import com.poo.miapi.modules.auth.entity.User;
import com.poo.miapi.modules.auth.enums.GlobalRole;
import com.poo.miapi.modules.repository.UserRepository;
import com.poo.miapi.service.security.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthApi {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmailAndDeletedAtIsNull(request.getEmail());

        boolean passwordMatches = false;

        if (user != null) {
            passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        } else {
            // Dummy para igualar el tiempo de respuesta si el user es null
            passwordEncoder.matches(request.getPassword(), "$2a$10$dummyHashToPreventTimingAttacks");
        }

        if (user == null || user.getDeletedAt() != null) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }

        if (!passwordMatches) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(user);
        UserResponse userResponse = toDto(user);

        return LoginResponse.builder().token(token).user(userResponse).build();
    }

    // Register
    public boolean register(RegisterRequest request) {
        // Validaciones básicas de entrada
        if (request == null || request.getEmail() == null || request.getEmail().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank() || request.getFirstName() == null
                || request.getFirstName().isBlank() || request.getLastName() == null
                || request.getLastName().isBlank()) {
            throw new IllegalArgumentException("Datos de registro incompletos o inválidos");
        }

        // Validar si el email ya está registrado
        if (userRepository.findByEmailAndDeletedAtIsNull(request.getEmail()) != null) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        User user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).blocked(false)
                .role(GlobalRole.USER).build();

        userRepository.save(user);
        return true;
    }

    // Change password
    public boolean changePassword(ChangePasswordRequest request) {
        // Validaciones básicas de entrada
        if (request == null || request.getUserId() == null || request.getCurrentPassword() == null
                || request.getCurrentPassword().isBlank() || request.getNewPassword() == null
                || request.getNewPassword().isBlank()) {
            throw new IllegalArgumentException("Datos de cambio de contraseña incompletos o inválidos");
        }

        User user = userRepository.findByIdAndDeletedAtIsNull(request.getUserId());
        if (user == null) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }

        // Validar contraseña actual
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("La contraseña actual no coincide");
        }

        // Validar que la nueva contraseña no sea igual a la anterior
        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new IllegalArgumentException("La nueva contraseña no puede ser igual a la anterior");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        // Confirmar que la contraseña fue cambiada correctamente
        return passwordEncoder.matches(request.getNewPassword(), user.getPassword());
    }

    // HELPERS

    private UserResponse toDto(User user) {
        return UserResponse.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName())
                .email(user.getEmail()).userRole(user.getRole()).build();
    }

}
