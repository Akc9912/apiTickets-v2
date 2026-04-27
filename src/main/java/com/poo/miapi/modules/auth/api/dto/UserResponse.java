package com.poo.miapi.modules.auth.api.dto;

import java.util.UUID;

import com.poo.miapi.modules.auth.enums.GlobalRole;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private GlobalRole userRole;
}
