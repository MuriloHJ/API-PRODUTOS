package com.br.apiprodutos.controller;

import com.br.apiprodutos.dto.ErroResponse;
import com.br.apiprodutos.dto.user.UserResponse;
import com.br.apiprodutos.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class UsuarioController
{
    private final UserService service;

    @Operation(
            description = "Entra o usuário no sistema para realizar requisições",
            summary = "Conectar"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário conectado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de requisição inválidos",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado, credenciais ausentes ou inválidas",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            )

    })
    @GetMapping("/me")
    public UserResponse me(@AuthenticationPrincipal Jwt jwt)
    {
        return service.getById(Long.valueOf(jwt.getSubject()));
    }
}
