package com.br.apiprodutos.mapper;

import com.br.apiprodutos.dto.produto.ProdutoCreateRequest;
import com.br.apiprodutos.dto.produto.ProdutoResponse;
import com.br.apiprodutos.dto.produto.ProdutoUpdateRequest;
import com.br.apiprodutos.entity.ProdutoEntity;
import java.util.*;
import org.springframework.stereotype.*;

@Component
public class ProdutoMapper
{

    /**
    * Converte os dados de criação para uma entidade Produto.
    * @Param request dados recebidos para criação
    */
    public ProdutoEntity toEntity(ProdutoCreateRequest request)
    {
        return ProdutoEntity.builder()
                .nome(request.nome())
                .preco(request.preco())
                .build();
    }

    /**
     * Converte uma entidade Produto para o DTO de resposta
     * @param produto produto entidade persistida
     * @return representação pública do produto
     */
    public ProdutoResponse toResponse(ProdutoEntity produto)
    {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getAtivo()
        );
    }

    /**Converte uma lista de Entidades para uma lista de DTOs de reposta*/
    public List<ProdutoResponse> toResponseList(List<ProdutoEntity> produtos)
    {
        return produtos.stream()
                .map(this::toResponse)
                .toList();
    }

    public void updateEntity(ProdutoUpdateRequest request, ProdutoEntity produto)
    {
        produto.setNome(request.nome());
        produto.setPreco(request.preco());
        produto.setAtivo(request.ativo());
    }
}
