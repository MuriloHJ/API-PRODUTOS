package com.br.apiprodutos.dto.produto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.math.*;


@Schema(description = "dados para atualização de produto")
public record ProdutoUpdateRequest(
        @NotBlank(message = "O nome do produto não pode ser vazio")
        @Size(min = 3 , max = 100)
        @Schema(example = "LG UltraGear 180hz painel ips")
        String nome,

        @NotNull(message = "O preço não pode ser vazio")
        @Positive(message = "Só é permitido preço com valores maior que 0")
        @Schema(example = "2000.99")
        BigDecimal preco,

        @NotNull(message = "O campo ativo não pode estar vazio")
        @Schema(example = "false")
        boolean ativo
) {
}
