package com.br.apiprodutos.excpetion;

public class ProdutoNaoEncotradoException extends RuntimeException {
    public ProdutoNaoEncotradoException(String message) {
        super(message);
    }
}
