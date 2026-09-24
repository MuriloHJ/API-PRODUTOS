package com.br.apiprodutos.dto.auth;

import com.br.apiprodutos.dto.user.UserResponse;

public record LoginResponse(
        String token,
        String tokenType,
        long espiraEm,
        UserResponse  user
) {
}
