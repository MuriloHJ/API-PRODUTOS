package com.br.apiprodutos.repository;

import com.br.apiprodutos.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositório responsável pelo acesso dos dados de produtos
 */

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long>
{
    /**
     * Verifica se existe produto com o nome informado
     * Ignora diferença entre letras maiúsculas e miniscúlas
     * @pamram nome nome no qual será pesquisado
     * @return {@code true} caso o produto já exista
     * */
    boolean existsByNomeIgnoreCase(String nome);

    /**
     * Busca produtos cujo o nome contenha o texto informado
     * @param nome parte do nome do produto
     * @return produtos encontrados
     * */
    List<ProdutoEntity> findByNomeContainingIgnoreCase(String nome);
}
