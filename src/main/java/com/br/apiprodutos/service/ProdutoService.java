package com.br.apiprodutos.service;

import com.br.apiprodutos.dto.produto.ProdutoCreateRequest;
import com.br.apiprodutos.dto.produto.ProdutoResponse;
import com.br.apiprodutos.dto.produto.ProdutoUpdateRequest;
import com.br.apiprodutos.entity.ProdutoEntity;
import com.br.apiprodutos.excpetion.ProdutoNaoEncotradoException;
import com.br.apiprodutos.mapper.ProdutoMapper;
import com.br.apiprodutos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoService(ProdutoRepository repository, ProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Cadastra um novo produto na base de dados após validar a unicidade do nome
     * @param request Objeto contendo os dados de entrada para criação do produto
     * @return DTO {@link ProdutoResponse} com os dados do produto persistido
     * @throws IllegalArgumentException Se já existir um produto cadastrado com o mesmo nome
     * */

    @Transactional
    public ProdutoResponse cadastrar(ProdutoCreateRequest request)
    {
        if(repository.existsByNomeIgnoreCase(request.nome()))
        {
            throw new IllegalArgumentException("Já existe produto com esse nome");
        }
        //converte DTO -> Entity
        ProdutoEntity produto = mapper.toEntity(request);
        produto.setAtivo(true);
        ProdutoEntity salvo = repository.save(produto);

        //Converte Entity -> DTO Response
        return mapper.toResponse(salvo);
    }

    /**
     * Retorna todos os produtos cadastrados
     * @return Lista de DTOs {@link ProdutoResponse} representando os produtos encontrados.Lista vazia caso nenhum produto seja encontrado
     */
    @Transactional(readOnly = true)
    public List<ProdutoResponse> listar()
    {
        List<ProdutoEntity> produtos = repository.findAll();

        return mapper.toResponseList(produtos);
    }

    /**
     * Busca um produto pelo seu identificador único
     * @param id Identificador do produto a ser localizaoo
     * @return DTO {@link ProdutoResponse} representando o produto encontrado
     * @throws ProdutoNaoEncotradoException Se nenhum produto for encontrado com o ID informado
     * */
    @Transactional(readOnly = true)
    public ProdutoResponse buscarPorId(Long id)
    {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new
                        ProdutoNaoEncotradoException("Produto não encontrado com id: " + id));
    }

    /**
     * Busca produtos cujo nome contenha o termo informado (case insensitive)
     * @param nome Termo ou trecho do nome do produto a ser pesquisado
     * @return Lista de DTOs {@link ProdutoResponse} correspondentes ao termo informado
     * */
    @Transactional(readOnly = true)
    public List<ProdutoResponse> buscarPorNome(String nome)
    {
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    /**
     * Atualiza todos os dados de um produto existente
     *
     * @param id Identificador do produto a ser atualizado
     * @param request DTO co os novos dados do produto
     * @return DTO {@link ProdutoResponse} com os dados do produto atualizados
     * @throws ProdutoNaoEncotradoException Se nenhum produto for encontrado para o ID informado
     */
     @Transactional
     public ProdutoResponse atualizar(Long id, ProdutoUpdateRequest request)
     {
         ProdutoEntity produto = repository.findById(id)
                 .orElseThrow(() -> new
                         ProdutoNaoEncotradoException("Produto não encontrado com id: " + id));
         mapper.updateEntity(request, produto);

         ProdutoEntity atualizado = repository.save(produto);

         return mapper.toResponse(atualizado);
     }

    /**
     * Remove um produto da bas de dados pelo seu identificador
     * @param id Identificador do produto a ser removido
     * @throws ProdutoNaoEncotradoException Se nennhum produto for encontrado com o ID informado
     * */


    @Transactional
    public void remover(Long id)
    {
        ProdutoEntity produto = repository.findById(id)
                .orElseThrow(() -> new
                        ProdutoNaoEncotradoException("Produto não encontrado com id: " + id));
        repository.delete(produto);
    }


}

