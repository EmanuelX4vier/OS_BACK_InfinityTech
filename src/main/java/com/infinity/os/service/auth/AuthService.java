package com.infinity.os.service.auth;

import com.infinity.os.dto.authdto.AuthTokens;
import com.infinity.os.dto.authdto.LoginRequestDTO;
import com.infinity.os.dto.userdto.UserRequestDTO;

public interface AuthService {

    AuthTokens refreshToken(String refreshToken);
    AuthTokens login(LoginRequestDTO request);
    void logout(String refreshToken);
    void register(UserRequestDTO request);
}
