package com.br.apiprodutos.dto.produto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.*;


/**
 * Representação pública de um produto retornado pela API
 *
 * @param id    identificação do produto
 * @param nome  nome do produto
 * @param preco preco de venda
 * @param ativo situação atual do produto
 */

@Schema(description = "Dados de um produto retornados pela API")
public record ProdutoResponse(
        @Schema(description = "identificador unico do produto", example = "1")
        Long id,

        @Schema(description = "nome do produto", example = "Mouse Logitech GPRO SuperLight")
        String nome,

        @Schema(description = "valor de venda do produto", example = "1200.00")
        BigDecimal preco,

        @Schema(description = "Indica se o produto esta ativo ou desativado", example = "true")
        boolean ativo
) {
}
