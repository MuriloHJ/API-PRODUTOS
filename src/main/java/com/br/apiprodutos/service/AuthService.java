package com.br.apiprodutos.service;

import com.br.apiprodutos.config.security.TokenService;
import com.br.apiprodutos.dto.auth.LoginRequest;
import com.br.apiprodutos.dto.auth.LoginResponse;
import com.br.apiprodutos.dto.auth.RegisterRequest;
import com.br.apiprodutos.dto.user.UserResponse;
import com.br.apiprodutos.entity.User;
import com.br.apiprodutos.mapper.UserMapper;
import com.br.apiprodutos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService
{
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserMapper mapper;


    @Transactional
    public UserResponse register(RegisterRequest request)
    {
        if(!repository.findByNome(request.nome()).isEmpty())
        {
            throw new RuntimeException("Nome de usuário já está em uso");
        }

        User user =  User.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .role(request.role())
                .build();

        return mapper.toResponse(repository.save(user));
    }


    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request)
    {
        User user = repository.findByNome(request.nome())
                .filter(u -> passwordEncoder.matches(request.senha(), u.getPassword()))
                .orElseThrow(() -> new BadCredentialsException("Credenciais inválidas"));
        return new LoginResponse(
                tokenService.generatedToken(user),
                "Barer",
                tokenService.getAccessToken(),
                mapper.toResponse(user)
        );
    }

}
