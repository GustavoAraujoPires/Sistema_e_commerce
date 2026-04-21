package com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta (int status, String mensagem, List<ErroCampo> erros){

    public static ErroResposta RespostaPadrao (String mensagem){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());// List.of() -> retorna uma lista vazia
    }

    public static ErroResposta conflito(String mensagem){
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }


}
