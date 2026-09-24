package com.br.apiprodutos.dto.auth;

import com.br.apiprodutos.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO de requisição para registro de usuário.
 *
 * @param nome  Nome do usuário. Não pode ser vazio. Ex.: "Marco Cola".
 * @param email Email único usado como login. Não pode ser vazio. Ex.: "marco@gmail.com".
 * @param senha Senha em texto. Não pode ser vazia. Ex.: "S3nh@Forte!".
 * @param role  Perfil de acesso (ADMIN ou USER). Não pode ser nulo. Ex.: ADMIN.
 */
@Schema(description = "Payload para registro de um novo usuário")
public record RegisterRequest(

        @Schema(description = "Nome do usuário", example = "João Silva")
        @NotBlank(message = "Nome não pode ser vazio")
        String nome,

        @Schema(description = "Email único usado como login", example = "joao@email.com")
        @NotBlank(message = "Email não pode ser vazio")
        String email,

        @Schema(description = "Senha em texto puro (será hasheada com BCrypt)", example = "S3nh@Forte!" )
        @NotBlank(message = "Senha não pode ser vazio")
        String senha,

        @Schema(description = "Perfil de acesso do usuário", example = "ADMIN")
        @NotNull(message = "Role não pode ser vazio")
        Role role
) {
}
