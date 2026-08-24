package com.infinity.os.service.refresh;

import com.infinity.os.entity.RefreshToken;

public record RefreshTokenResult (RefreshToken refreshToken, String token){
}
