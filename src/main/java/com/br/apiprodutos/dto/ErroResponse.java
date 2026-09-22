package com.br.apiprodutos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import com.br.apiprodutos.dto.ErroResponse;
import java.time.LocalDateTime;

public record ErroResponse(
        @Schema(description = "Código Status HTTP", example = "404")
        Integer Status,

        @Schema(description = "Descrição tipo de erro",example = "Recurso não encontrado")
        String erro,

        @Schema(description = "Mensagem detalhada do erro",example = "Produto não encontrado com id = 1")
        String mensagem,

        @Schema(description = "URL da requisição que originou o erro",example = "/produto/1")
        String caminho,

        @Schema(description = "Data e hora do erro",example = "2026-08-22T19:17:46")
        LocalDateTime timestamp
){
    public static @NotNull ErroResponse criar(Integer status, String erro, String mensagem, String caminho){
        return new ErroResponse(status,erro,mensagem,caminho,LocalDateTime.now());
    }
}