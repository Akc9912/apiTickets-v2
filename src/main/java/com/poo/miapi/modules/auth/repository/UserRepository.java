package com.poo.miapi.modules.auth.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poo.miapi.modules.auth.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByIdAndDeletedAtIsNull(UUID id);

    User findByEmailAndDeletedAtIsNull(String email);
}
