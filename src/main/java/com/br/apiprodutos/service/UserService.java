package com.br.apiprodutos.service;

import com.br.apiprodutos.dto.user.UserResponse;
import com.br.apiprodutos.mapper.UserMapper;
import com.br.apiprodutos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    @Transactional(readOnly = true)
    public UserResponse getById(Long id)
    {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id));
    }
}
