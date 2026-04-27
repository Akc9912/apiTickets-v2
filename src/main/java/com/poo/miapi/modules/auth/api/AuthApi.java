package com.poo.miapi.modules.auth.api;

import com.poo.miapi.modules.auth.api.dto.LoginRequest;
import com.poo.miapi.modules.auth.api.dto.LoginResponse;

public interface AuthApi {
    LoginResponse login(LoginRequest request);
}
