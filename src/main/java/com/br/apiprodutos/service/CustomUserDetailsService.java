package com.br.apiprodutos.service;

import com.br.apiprodutos.entity.User;
import com.br.apiprodutos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;

    /**
     *
     * @param nome the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException
    {
        User user = userRepository.findByNome(nome)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado com nome de: " + nome));

        return user;
    }
}
