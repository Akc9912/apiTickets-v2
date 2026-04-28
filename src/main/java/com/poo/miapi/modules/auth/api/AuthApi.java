package com.poo.miapi.modules.auth.api;

import com.poo.miapi.modules.auth.api.dto.ChangePasswordRequest;
import com.poo.miapi.modules.auth.api.dto.LoginRequest;
import com.poo.miapi.modules.auth.api.dto.LoginResponse;
import com.poo.miapi.modules.auth.api.dto.RegisterRequest;

public interface AuthApi {
    LoginResponse login(LoginRequest request);

    boolean register(RegisterRequest request);

    boolean changePassword(ChangePasswordRequest request);
}
