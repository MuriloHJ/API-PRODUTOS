package com.br.apiprodutos.mapper;

import com.br.apiprodutos.dto.user.UserResponse;
import com.br.apiprodutos.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserMapper
{

    /**
     * Converte uma entidade Produto para o DTO de resposta
     * @param user produto entidade persistida
     * @return representação pública do produto
     */
    public UserResponse toResponse(User user)
    {
       return new UserResponse(
               user.getId(),
               user.getNome(),
               user.getEmail(),
               user.getRole()
       );
    }

    /**Converte uma lista de Entidades para uma lista de DTOs de reposta*/
    public List<UserResponse> toResponseList(List<User> users)
    {
        return users.stream()
                .map(this::toResponse)
                .toList();
    }


}
