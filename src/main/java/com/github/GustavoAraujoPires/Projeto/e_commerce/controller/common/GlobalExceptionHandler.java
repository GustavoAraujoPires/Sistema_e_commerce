package com.github.GustavoAraujoPires.Projeto.e_commerce.controller.common;

import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto.ErroCampo;
import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto.ErroResposta;
import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.ClienteIdNaoEncontradoException;
import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.ClienteInvalidoException;
import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.ProdutoInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaErro = fieldErrors.stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação", listaErro);
    }

    @ExceptionHandler(ClienteInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handerClienteInvalidoException(ClienteInvalidoException e) {
        return  ErroResposta.RespostaPadrao(e.getMessage());
    }

    @ExceptionHandler(ClienteIdNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta handerClienteInvalido(ClienteIdNaoEncontradoException e) {
        return ErroResposta.RespostaPadrao(e.getMessage());
    }

    @ExceptionHandler(ProdutoInvalidoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta handerProdutosInvalidoException(ProdutoInvalidoException e) {
        return  ErroResposta.RespostaPadrao(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handerErroNaoTratado(RuntimeException e){
        return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro inesperado. Entre em contato com a administração.", List.of());
    }

}

