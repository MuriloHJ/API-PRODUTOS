package com.br.apiprodutos.excpetion;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.br.apiprodutos.dto.*;

import javax.naming.AuthenticationException;
import javax.swing.text.html.HTML;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import com.br.apiprodutos.dto.ErroResponse;

@RestControllerAdvice
public class GlobalExcpetionHandler
{
    @ExceptionHandler(ProdutoNaoEncotradoException.class)
    public ResponseEntity<ErroResponse> tratarProdutoNaoEnconrado(
            ProdutoNaoEncotradoException ex, HttpServletRequest request)
    {
        ErroResponse erro = ErroResponse.criar(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> tratarRegraDeNegocio(
            IllegalAccessException ex, HttpServletRequest request
    ){
        ErroResponse erro = ErroResponse.criar(
                HttpStatus.BAD_REQUEST.value(),
                "Requisição inválida",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarErrosDeValidacao(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        // Captura todas as falhas nos campos e concatena as mensagens
        String mensagensDeErro = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErroResponse erro = ErroResponse.criar(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação",
                mensagensDeErro,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErroResponse> handleAuthentication(AuthenticationException ex,HttpServletRequest request)
    {
        ErroResponse erro = new ErroResponse(
                401,
                "Não autenticado",
                "Credenciais ausentes ou inválidas.",
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
    }
}


