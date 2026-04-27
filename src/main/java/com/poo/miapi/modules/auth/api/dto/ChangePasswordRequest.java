package com.poo.miapi.modules.auth.api.dto;

import java.util.UUID;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordRequest {
    private UUID userId;
    private String currentPassword;
    private String newPassword;
}
