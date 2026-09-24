package com.br.apiprodutos.controller;

import com.br.apiprodutos.dto.ErroResponse;
import com.br.apiprodutos.dto.auth.LoginRequest;
import com.br.apiprodutos.dto.auth.LoginResponse;
import com.br.apiprodutos.dto.auth.RegisterRequest;
import com.br.apiprodutos.dto.user.UserResponse;
import com.br.apiprodutos.service.AuthService;
import com.br.apiprodutos.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final UserService service;
    private final AuthService authService;
    @Operation(
            description = "Registra o usuário no banco para realizar requisições",
            summary = "Registrar"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário registrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de requisição inválidos",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Usuário já existe",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado, credenciais ausentes ou inválidas",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            )
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponse>register(@Valid @RequestBody RegisterRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

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
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse>login(@Valid @RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }
}
