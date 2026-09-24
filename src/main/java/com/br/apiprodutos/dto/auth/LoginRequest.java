package com.br.apiprodutos.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "O nome não pode ser vazio")
        String nome,
        @NotBlank(message = "A senha não pode ser vazia")
        String senha
) {
}
