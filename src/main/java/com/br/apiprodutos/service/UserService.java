package com.br.apiprodutos.service;

import com.br.apiprodutos.dto.user.UserRegisterRequest;
import com.br.apiprodutos.dto.user.UserResponse;
import com.br.apiprodutos.entity.User;
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

    /**
     *
     * @param request
     * @return
     */
    @Transactional
    public UserResponse register(UserRegisterRequest request)
    {
        if(repository.existsByEmail(request.email()))
        {
            throw new RuntimeException("Erro usuário já existe");
        }

        User user = User.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(request.senha())
                .role(request.role())
                .build();

        User salvo = repository.save(user);

        return new UserResponse(salvo.getId(), salvo.getNome(), salvo.getEmail(),salvo.getRole());
    }
}
