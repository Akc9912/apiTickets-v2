package com.poo.miapi.modules.auth.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.poo.miapi.modules.auth.enums.GlobalRole;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;
    private GlobalRole role;
    private boolean blocked;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder.Default
    private LocalDateTime deletedAt = null; //soft-delete

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /// Usa siempre el formato ROLE_SUPER_ADMIN para el rol de superadmin
        String roleName = role != null ? role.name() : "USER";
        return java.util.List.of(new SimpleGrantedAuthority("ROLE_" + roleName));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
