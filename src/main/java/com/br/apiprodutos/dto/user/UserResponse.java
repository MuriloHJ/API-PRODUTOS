package com.br.apiprodutos.dto.user;

import com.br.apiprodutos.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de resposta com os dados de um usuário.
 *
 * @param id    Identificador único do usuário. Ex.: 1.
 * @param nome  Nome do usuário. Ex.: "Andrei Sapelli".
 * @param email Email do usuário. Ex.: "gustavo@gmail.com".
 * @param role  Perfil de acesso do usuário (ADMIN ou USER). Ex.: ADMIN.
 */
@Schema(description = "Dados de um usuário retornado pela API")
public record UserResponse(

        @Schema(description = "Identificador único do usuário", example = "1")
        Long id,

        @Schema(description = "Nome do usuário", example = "João Silva")
        String nome,

        @Schema(description = "Email do usuário", example = "joao@email.com")
        String email,

        @Schema(description = "Perfil de acesso do usuário", example = "ADMIN")
        Role role
) {
}