package com.br.apiprodutos.repository;

import com.br.apiprodutos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByNome(String nome);
    boolean existsByEmail(String email);
}
