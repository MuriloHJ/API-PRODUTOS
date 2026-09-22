package com.br.apiprodutos.dto.produto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;


import java.math.*;


public record ProdutoCreateRequest(

    @Schema(
            description = "Nome do produto",
            example = "AlienWare"
    )
    @NotBlank(message = "o nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve possuir entre minimo 3 e no máximo 100 caracteres")
    String nome,

    @Schema(
            description = "O valor de venda do produto",
            example = "6000.00"
    )
    @NotNull(message = "o preçoo é obrigatório")
    @Positive(message = "o preço deve maior que zero")
    BigDecimal preco
    ){
}
