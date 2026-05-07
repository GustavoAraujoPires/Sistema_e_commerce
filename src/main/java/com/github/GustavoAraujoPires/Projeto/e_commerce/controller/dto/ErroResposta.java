package com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta (int status, String mensagem, List<ErroCampo> erros){

    public static ErroResposta PagamentoInvalido (String mensagem){
        return new ErroResposta(HttpStatus.PAYMENT_REQUIRED.value(), mensagem, List.of());// List.of() -> retorna uma lista vazia
    }

    public static ErroResposta naoPermitido (String mensagem){
        return new ErroResposta(HttpStatus.METHOD_NOT_ALLOWED.value(), mensagem, List.of());// List.of() -> retorna uma lista vazia
    }

    public static ErroResposta naoAltorizado(String mensagem){
        return new ErroResposta(HttpStatus.UNAUTHORIZED.value(), mensagem, List.of());
    }
    public static ErroResposta idNaoEncontrado(String mensagem){
        return new ErroResposta(HttpStatus.NOT_FOUND.value(), mensagem, List.of());
    }

    public static ErroResposta produtoNaoEncontrado(String mensagem){
        return new ErroResposta(HttpStatus.NOT_FOUND.value(), mensagem, List.of());
    }

    public static ErroResposta clienteNaoEncontrado(String mensagem){
        return new ErroResposta(HttpStatus.NOT_FOUND.value(), mensagem, List.of());
    }

    public static ErroResposta conflito(String mensagem){
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}
